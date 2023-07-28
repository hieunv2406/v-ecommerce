package com.vm.authentication.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/hello")
public class PublicController {

    @GetMapping("/all")
    public String allAccess() {
        String a = "B";
        String b = "c";
        b = "a";
        System.out.println(b);
        String a1 = new String("B");
        a1 = a1 + "C";
        return "Public Content.";
    }
}
