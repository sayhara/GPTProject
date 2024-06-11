package com.GPTProject.controller;

import com.GPTProject.entity.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class MainController {

    @GetMapping("/")
    public String home(Account account, Model model){
        if(account!=null){
            model.addAttribute(account);
        }
        return "main";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
