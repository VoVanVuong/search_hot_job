package edu.vku.searchjob.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class accountController {
    @GetMapping("/singUp")
    public String signUp(){
        return "admin/account/signUp";
    }
    @GetMapping("/signIn")
    public String signIn(){
        return "admin/account/signIn";
    }
}
