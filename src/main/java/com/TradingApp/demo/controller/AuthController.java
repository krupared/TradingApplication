package com.TradingApp.demo.controller;

import com.TradingApp.demo.config.JwtConstant;
import com.TradingApp.demo.config.JwtProvider;
import com.TradingApp.demo.modal.AppUser;
import com.TradingApp.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<AppUser> register(@RequestBody AppUser appUser) throws Exception {



        AppUser isEmailExist = userRepository.findByEmail(appUser.getEmail());
        if (isEmailExist != null) {
            throw new Exception("Email already exists");
        }

        AppUser newAppUser = new AppUser();
        newAppUser.setEmail(appUser.getEmail());
        newAppUser.setPassword(appUser.getPassword());
        newAppUser.setFullName(appUser.getFullName());

        AppUser savedAppUser = userRepository.save(newAppUser);
        Authentication auth = new UsernamePasswordAuthenticationToken(appUser.getEmail(), appUser.getPassword());

        SecurityContextHolder.getContext().setAuthentication(auth);
        String jwt = JwtProvider.generateToken(auth);

        return new ResponseEntity<>(savedAppUser, HttpStatus.CREATED);

    }
}
