package com.enquery.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnqueryController {

    @GetMapping("/")
    public String index(){
        return "/index";
    }

}