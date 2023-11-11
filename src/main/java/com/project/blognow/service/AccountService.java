package com.project.blognow.service;
import com.project.blognow.model.Account;
import com.project.blognow.model.Authority;
import com.project.blognow.repository.AuthorityRepository;
import com.project.blognow.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class AccountService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthorityRepository authorityRepository;
    public Account save(Account account){
        if (account.getAuthorities().isEmpty()) {
            Set<Authority> authorities = new HashSet<>();
            authorityRepository.findById("ROLE_USER").ifPresent(authorities::add);
            account.setAuthorities(authorities);
        }
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return userRepository.save(account);
    }

    public Optional<Account> findByEmail(String email) {
        return userRepository.findOneByEmailIgnoreCase(email);
    }
}
