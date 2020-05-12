package com.diego.exxhangesecurity.controller;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import com.diego.exxhangesecurity.dto.UserPostRequest;
import com.diego.exxhangesecurity.entity.Authority;
import com.diego.exxhangesecurity.entity.User;
import com.diego.exxhangesecurity.repository.AuthorityRepository;
import com.diego.exxhangesecurity.repository.UserRepository;
import com.diego.exxhangesecurity.util.Constants;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/signin")
public class SignInController {

    private final UserRepository userRepository;

    private final AuthorityRepository authorityRepository;

    private final PasswordEncoder passwordEncoder;

    public SignInController(UserRepository userRepository, PasswordEncoder passwordEncoder, 
            AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityRepository = authorityRepository;
    }

    @PostMapping
    User signin(@Valid @RequestBody UserPostRequest userRequest) {

        User user = new User(userRequest.getUsername(), passwordEncoder.encode(userRequest.getPassword()));
        Authority client = authorityRepository.findByName(Constants.ROLE_CLIENT);
        Collection<Authority> authorities = new ArrayList<>();
        authorities.add(client);
        user.setAuthorities(authorities);
        return userRepository.save(user);
    }

    @PostMapping("/validateUsername")
    Boolean emailExists(@RequestParam String username) {
        return userRepository.existsByUsername(username);
    }

}