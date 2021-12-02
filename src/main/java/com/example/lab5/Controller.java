package com.example.lab5;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@org.springframework.stereotype.Controller
public class Controller {
    private Service service;
    @Autowired
    public Controller(Service service){
        this.service=service;
    }
    private String url="https://panoramafirm.pl/";

    @GetMapping("/")
    public String getData(Model model) throws IOException {
        return service.getData(model);
    }
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public String getUsers(@ModelAttribute PageContent data, Model model){
        return service.getUsers(data,model);
    }



}
