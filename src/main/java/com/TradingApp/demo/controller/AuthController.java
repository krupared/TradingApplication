package com.TradingApp.demo.controller;

import com.TradingApp.demo.modal.AppUser;
import com.TradingApp.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<AppUser> register(@RequestBody AppUser appUser) {

        AppUser newAppUser = new AppUser();
        newAppUser.setEmail(appUser.getEmail());
        newAppUser.setPassword(appUser.getPassword());
        newAppUser.setFullName(appUser.getFullName());

        AppUser savedAppUser = userRepository.save(newAppUser);

        return new ResponseEntity<>(savedAppUser, HttpStatus.CREATED);

    }
}
