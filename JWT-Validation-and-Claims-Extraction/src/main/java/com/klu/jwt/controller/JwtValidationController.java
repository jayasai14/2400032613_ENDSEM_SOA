package com.klu.jwt.controller;

import com.klu.jwt.dto.TokenRequest;
import com.klu.jwt.dto.TokenValidationResponse;
import com.klu.jwt.service.JwtTokenService;
import io.jsonwebtoken.JwtException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class JwtValidationController {

    private final JwtTokenService jwtTokenService;

    public JwtValidationController(JwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }

    @PostMapping("/validate")
    public ResponseEntity<TokenValidationResponse> validateToken(@Valid @RequestBody TokenRequest request) {
        try {
            Map<String, Object> claims = jwtTokenService.extractClaims(request.getToken());
            return ResponseEntity.ok(
                    new TokenValidationResponse(true, "Token is valid", claims));
        } catch (JwtException | IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                    new TokenValidationResponse(false, "Token is invalid or expired", Collections.emptyMap()));
        }
    }
}