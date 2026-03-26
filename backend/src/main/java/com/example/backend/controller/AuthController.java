package com.example.backend.controller;

import com.example.backend.dto.SignupRequest;
import com.example.backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("signup")
    public String signup(@RequestBody SignupRequest request){
        try{
            authService.signup(request);
            return "User registered successfully";
        }
        catch (RuntimeException e){
            return e.getMessage();
        }
    }
}
