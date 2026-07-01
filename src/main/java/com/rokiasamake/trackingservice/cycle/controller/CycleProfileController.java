package com.rokiasamake.trackingservice.cycle.controller;

import com.rokiasamake.trackingservice.cycle.dto.response.ApiResponse;
import com.rokiasamake.trackingservice.cycle.dto.request.CreateCycleProfileRequest;
import com.rokiasamake.trackingservice.cycle.dto.request.UpdateCycleProfileRequest;
import com.rokiasamake.trackingservice.cycle.dto.response.CycleProfileResponse;
import com.rokiasamake.trackingservice.cycle.service.interfaces.CycleProfileService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.rokiasamake.trackingservice.security.CurrentUser;
import jakarta.servlet.http.HttpServletRequest;

import java.util.UUID;

@Tag(
        name = "🌸 Profil menstruel",
        description = "Gestion du profil menstruel de l'utilisatrice."
)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tracking/cycle/profile")
@SecurityRequirement(name = "Bearer Authentication")
public class CycleProfileController {

    private final CycleProfileService service;
    private final CurrentUser currentUser;

    @PostMapping
    public ApiResponse<CycleProfileResponse> createProfile(
            HttpServletRequest request,
            @Valid @RequestBody CreateCycleProfileRequest body) {

        return ApiResponse.success(
                "Profil menstruel créé avec succès.",
                service.createProfile(
                        currentUser.getMemberId(request),
                        body)
        );
    }

    @GetMapping
    public ApiResponse<CycleProfileResponse> getProfile(
            HttpServletRequest request) {

        return ApiResponse.success(
                "Profil menstruel récupéré avec succès.",
                service.getProfile(
                        currentUser.getMemberId(request))
        );
    }

    @PutMapping
    public ApiResponse<CycleProfileResponse> updateProfile(
            HttpServletRequest request,
            @Valid @RequestBody UpdateCycleProfileRequest body) {

        return ApiResponse.success(
                "Profil menstruel mis à jour avec succès.",
                service.updateProfile(
                        currentUser.getMemberId(request),
                        body)
        );
    }

}