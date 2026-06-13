package com.example.backend.service;

import com.example.backend.entity.RefreshToken;
import com.example.backend.repository.RefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public String createRefreshToken(Long userId) {

        RefreshToken refreshToken =
                refreshTokenRepository.findByUserId(userId)
                        .orElse(new RefreshToken());

        refreshToken.setUserId(userId);
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setUpdatedAt(LocalDateTime.now());
        refreshToken.setExpiryDate(LocalDateTime.now().plusDays(7));

        if (refreshToken.getCreatedAt() == null) {
            refreshToken.setCreatedAt(LocalDateTime.now());
        }

        refreshTokenRepository.save(refreshToken);

        return refreshToken.getToken();
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }
}
