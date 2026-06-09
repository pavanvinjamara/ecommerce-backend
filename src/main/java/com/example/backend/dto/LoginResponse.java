package com.example.backend.dto;

public class LoginResponse {

    private Long id;
    private String email;
    private String token;
    private String refreshToken;


    public LoginResponse(Long id, String email, String token, String refreshToken){
        this.id = id;
        this.email = email;
        this.token = token;
        this.refreshToken = refreshToken;
    }

    // ✅ ADD THESE
    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getToken() {
        return token;
    }


    public String getRefreshToken() {
        return refreshToken;
    }
}
