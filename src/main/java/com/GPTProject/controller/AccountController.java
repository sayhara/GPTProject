package com.GPTProject.controller;

import com.GPTProject.dto.SignUpDto;
import com.GPTProject.entity.Account;
import com.GPTProject.service.AccountService;
import com.GPTProject.validation.SignUpDtoValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class AccountController {

    private final SignUpDtoValidator signUpDtoValidator;
    private AccountService accountService;

    @InitBinder("signUpDto") // @Valid 검증전 실행
    public void signUpDtoInitBinder(WebDataBinder webDataBinder){
        webDataBinder.addValidators(signUpDtoValidator);
    }

    @GetMapping("/sign-up")
    public String signUpForm(Model model){
        model.addAttribute("login", new SignUpDto());
        return "account/sign-up";
    }

    @PostMapping("/sign-up")
    public String signUpSubmit(@Valid SignUpDto signUpDto, Errors errors){

        if(errors.hasErrors()){
            return "account/sign-up";
        }
        // 로그인 처리
        Account account=accountService.join(signUpDto);
        accountService.login(account);

        return "redirect:/";

    }
}
