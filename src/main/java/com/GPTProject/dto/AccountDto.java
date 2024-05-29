package com.GPTProject.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AccountDto {

    @NotBlank
    //@Length
    private String username;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;


}
