package com.GPTProject.validation;

import com.GPTProject.dto.SignUpDto;
import com.GPTProject.repository.AccountRepository;
import com.GPTProject.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class SignUpDtoValidator implements Validator {

    private final AccountRepository accountRepository;
    private final AccountService accountService;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(SignUpDto.class);
    }

    @Override
    public void validate(Object target, Errors errors) {

        SignUpDto signUpDto= (SignUpDto) target;
        if(accountRepository.existsByEmail(signUpDto.getEmail())){
            errors.rejectValue("email","invalid email","이미 사용중인 이메일입니다");
        }

        if(accountRepository.existsByUsername(signUpDto.getUsername())){
            errors.rejectValue("username","invalid username","이미 사용중인 아이디입니다");
        }

    }
}
