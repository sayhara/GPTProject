package com.GPTProject.service;

import com.GPTProject.config.UserAccount;
import com.GPTProject.dto.SignUpDto;
import com.GPTProject.entity.Account;
import com.GPTProject.repository.AccountRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountService implements UserDetailsService {

    private final AccountRepository accountRepository;

    public Account join(SignUpDto signUpDto){

        Account account=Account.builder()
                .email(signUpDto.getEmail())
                .username(signUpDto.getUsername())
                .password(signUpDto.getPassword())
                .build();
        accountRepository.save(account);
        return account;

    }

    public void login(Account account){

        UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(
                new UserAccount(account),
                account.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_USER")));
        SecurityContextHolder.getContext().setAuthentication(token);

    }

    @Override
    public UserDetails loadUserByUsername(String check) throws UsernameNotFoundException {

        Account account=accountRepository.findByEmail(check);
        if(account==null){
            account=accountRepository.findByUsername(check);
        }

        if(account==null){
            throw new UsernameNotFoundException(check);
        }

        return new UserAccount(account);
    }
}
