package com.rokiasamake.trackingservice.security;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CurrentUser {

    public UUID getMemberId(HttpServletRequest request) {
        return UUID.fromString(request.getHeader("X-Member-Id"));
    }

    public String getEmail(HttpServletRequest request) {
        return request.getHeader("X-User-Email");
    }

    public String getRole(HttpServletRequest request) {
        return request.getHeader("X-Role");
    }

}