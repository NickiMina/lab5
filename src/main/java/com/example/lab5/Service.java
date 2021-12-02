package com.example.lab5;

import ezvcard.Ezvcard;
import ezvcard.VCardVersion;
import ezvcard.property.Revision;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.Jsoup;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import ezvcard.VCard;

@org.springframework.stereotype.Service
public class Service {
    PageContent dataSearched = new PageContent();
    List<ServiceModel> userList = new ArrayList<ServiceModel>();

    public String getData(Model model) {
        PageContent content = new PageContent();
        model.addAttribute("data", content);
        return "main";
    }
    public String getUsers(@ModelAttribute PageContent data, Model model) {
        Document document = null;
        Elements elements;
        int i=0;

        if(data.getName() != null && !data.getName().isEmpty()) {
            dataSearched.setName(data.getName());
            dataSearched.setPage(1);
        }
        try {
            document = Jsoup.connect("https://panoramafirm.pl/" + dataSearched.getName() + "/firmy,"+ dataSearched.getPage() + ".html").get();
            userList.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }

        elements = document.select("li.card.company-item");

        for (Element e : elements) {
            ServiceModel user = new ServiceModel();

            String name = e.select("h2").text();
            String web = e.select("a.icon-website").attr("href");
            String field=e.selectFirst(".item-content").text();

            user.setName(name);
            user.setLink(web);
            user.setvCard(String.valueOf(i++));
            user.setServiceField(field);
            userList.add(user);
        }

        model.addAttribute("users", userList);
        return "users";
    }
    public ResponseEntity<Resource> getCard(@ModelAttribute String userForCard, Model model) {
        VCard card = new VCard();
        ServiceModel user = new ServiceModel();
        HttpHeaders headers = new HttpHeaders();
        String nameFile = "cardUser"+userForCard+".vcf";
        File file= new File(nameFile);

        for(ServiceModel u: userList){
            if(u.getvCard().equals(userForCard)){
                user.setName(u.getName());
                user.setLink(u.getLink());
            }
        }

        card.setRevision(Revision.now());
        card.setFormattedName(user.getName());
        card.addUrl(user.getLink());


        try {
            Ezvcard.write(card).version(VCardVersion.V4_0).go(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="+nameFile);

        Resource fileSystemResource = new FileSystemResource(nameFile);
        return ResponseEntity.ok()
                .headers(headers)
                .body(fileSystemResource);
    }

}
