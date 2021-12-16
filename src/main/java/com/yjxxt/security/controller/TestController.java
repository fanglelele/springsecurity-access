package com.yjxxt.security.controller;


import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {


    @GetMapping("test")
    //@Secured(value = {"ROLE_CC"})
    @PreAuthorize("hasAuthority('101010')")
    public String test(){
        return "Hello SpringSecurity";
    }
}
