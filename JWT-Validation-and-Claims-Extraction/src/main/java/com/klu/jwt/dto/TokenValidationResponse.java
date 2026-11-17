package com.klu.jwt.dto;

import java.util.Map;

public class TokenValidationResponse {
    private boolean valid;
    private String message;
    private Map<String, Object> claims;

    public TokenValidationResponse(boolean valid, String message, Map<String, Object> claims) {
        this.valid = valid;
        this.message = message;
        this.claims = claims;
    }

    public boolean isValid() { return valid; }
    public String getMessage() { return message; }
    public Map<String, Object> getClaims() { return claims; }
}