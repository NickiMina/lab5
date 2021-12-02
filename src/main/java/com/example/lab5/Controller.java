package com.example.lab5;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Controller
@RequestMapping("pracownik")
public class Controller {
    private String url="https://panoramafirm.pl/";

    @GetMapping
    public String getEmployees(@RequestParam String serviceField)  {
        String path=url+serviceField;
        try {
            Document employeesDoc = Jsoup.connect(path).get();
            Elements employeeElements = employeesDoc.select(".user-info");

            List<ServiceModel> employees = new ArrayList<>();
           for(Element element:employeeElements){
               ServiceModel serviceModel=new ServiceModel();

               serviceModel.setName(element.selectFirst("h3").text());
               serviceModel.setServiceField(element.selectFirst(".item-content").text());
               serviceModel.setLink(element.selectFirst("a.fullprofile-link").attr("href"));

               employees.add(serviceModel);

           }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
