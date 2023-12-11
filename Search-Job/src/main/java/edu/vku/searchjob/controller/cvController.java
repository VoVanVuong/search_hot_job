package edu.vku.searchjob.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class cvController {
    @GetMapping("user/cv")
public String listCv(){
    return "user/listCv";
}
}
