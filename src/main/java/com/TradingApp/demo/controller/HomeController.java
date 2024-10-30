package com.TradingApp.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public String home() {
        return "trading home";
    }

    @GetMapping("/api")
    public String secureRoute() {
        return "trading secure home";
    }
}
