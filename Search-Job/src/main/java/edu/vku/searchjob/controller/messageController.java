package edu.vku.searchjob.controller;

import edu.vku.searchjob.service.IAccountService;
import edu.vku.searchjob.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class messageController {
    @Autowired
    private IAccountService iAccountService;
    @Autowired
    private IMessageService iMessageService;
    @GetMapping("user/message")
    public String messageUser(Model model, Principal p){
        return "user/message";
    }
}
