package com.example.lab5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@org.springframework.stereotype.Controller
public class Controller {
    private Service service;
    @Autowired
    public Controller(Service service){
        this.service=service;
    }

    @GetMapping("/page")
    public String getWebContent(Model model) throws IOException {
        return service.getData(model);
    }
    @RequestMapping(value = "/page/users", method = RequestMethod.POST)
    public String getUsers(@ModelAttribute PageContent data, Model model){
        return service.getEmp(data,model);
    }
    @RequestMapping(value = "/card/{user}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Resource> getVcard(@PathVariable String user, Model model) throws IOException {
        return service.getCard(user, model);
    }


}
