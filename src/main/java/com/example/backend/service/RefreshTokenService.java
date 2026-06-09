package com.example.backend.service;

import com.example.backend.entity.RefreshToken;
import com.example.backend.repository.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    public String createRefreshToken(Long userId) {
        // Delete old token for this user (one token per user)
        refreshTokenRepository.deleteByUserId(userId);

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUserId(userId);
        refreshToken.setToken(UUID.randomUUID().toString()); // random secure string
        refreshToken.setCreatedAt(LocalDateTime.now());
        refreshToken.setUpdatedAt(LocalDateTime.now());

        refreshToken.setExpiryDate(
                LocalDateTime.now().plusDays(7)
        );

        refreshTokenRepository.save(refreshToken);
        return refreshToken.getToken();
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }
}
