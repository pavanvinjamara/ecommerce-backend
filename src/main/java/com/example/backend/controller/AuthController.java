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

/*
Step 1:- Create a class AuthController
         above the class mention the annotations
         @RestController This class handles REST API requests.
                         It automatically returns JSON or text response.
         @RequestMapping("/")  All APIs inside this controller start with this

Step 2:- get the authService to perform business logic
         @Autowired
         private AuthService authService;

         Controller needs service layer to perform business logic.
*/


/*
Step 6️⃣ Create Controller (API Layer)

Controller receives HTTP requests from React frontend.

Example:

React → POST /signup → Spring Boot Controller
Create Folder
controller
Create File
AuthController.java
Code
package com.example.usermanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.usermanagement.dto.SignupRequest;
import com.example.usermanagement.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequest request) {

        authService.signup(request);

        return "User registered successfully";
    }

}
Line-by-Line Understanding
@RestController
@RestController

Means:

This class handles REST API requests.

It automatically returns JSON or text response.

Base URL
@RequestMapping("/api/auth")

All APIs inside this controller start with:

/api/auth

Example:

/api/auth/signup
Dependency Injection
@Autowired
private AuthService authService;

Controller needs service layer to perform business logic.

Spring injects it automatically.

API Endpoint
@PostMapping("/signup")

Creates an API:

POST /api/auth/signup
Request Body
@RequestBody SignupRequest request

Spring converts JSON → DTO.

Example request from React:

{
 "name":"Pavan",
 "email":"pavan@gmail.com",
 "password":"123456"
}
Call Service
authService.signup(request);

Controller calls the service layer.

Response
return "User registered successfully";

Response sent to frontend.

Full Flow Now
React Signup Form
        ↓
POST /api/auth/signup
        ↓
AuthController
        ↓
AuthServiceImpl
        ↓
UserRepository
        ↓
Database
Your Folder Structure Now
entity
repository
dto
service
   AuthService.java
   impl
      AuthServiceImpl.java
controller
   AuthController.java

✅ Your basic signup backend is now complete.

Next steps in real projects usually are:

1️⃣ Exception handling
2️⃣ Password hashing (very important)
3️⃣ Login API
4️⃣ JWT authentication
5️⃣ Spring Security

If you want, I can show you the correct industry-level login/signup flow used in
*/