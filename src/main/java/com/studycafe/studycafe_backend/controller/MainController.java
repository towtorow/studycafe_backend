package com.studycafe.studycafe_backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    @GetMapping("/try")
    public String root() {
        return "연결 성공!";
    }
}
