package com.rokiasamake.trackingservice.symptom.controller;

import com.rokiasamake.trackingservice.cycle.dto.response.ApiResponse;
import com.rokiasamake.trackingservice.security.CurrentUser;
import com.rokiasamake.trackingservice.symptom.dto.request.CreateSymptomRequest;
import com.rokiasamake.trackingservice.symptom.dto.request.UpdateSymptomRequest;
import com.rokiasamake.trackingservice.symptom.dto.response.SymptomResponse;
import com.rokiasamake.trackingservice.symptom.service.interfaces.SymptomService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(
        name = "🤒 Symptômes",
        description = "Gestion des symptômes enregistrés pendant un cycle menstruel."
)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tracking")
@SecurityRequirement(name = "Bearer Authentication")
public class SymptomController {

    private final SymptomService service;
    private final CurrentUser currentUser;

    @PostMapping("/symptoms")
    public ApiResponse<SymptomResponse> createSymptom(
            HttpServletRequest request,
            @Valid @RequestBody CreateSymptomRequest body) {

        return ApiResponse.success(
                "Symptôme enregistré avec succès.",
                service.createSymptom(
                        currentUser.getMemberId(request),
                        body
                )
        );
    }

    @GetMapping("/cycles/{cycleId}/symptoms")
    public ApiResponse<List<SymptomResponse>> getCycleSymptoms(
            HttpServletRequest request,
            @PathVariable UUID cycleId) {

        return ApiResponse.success(
                "Symptômes récupérés avec succès.",
                service.getCycleSymptoms(
                        currentUser.getMemberId(request),
                        cycleId
                )
        );
    }

    @PutMapping("/symptoms/{symptomId}")
    public ApiResponse<SymptomResponse> updateSymptom(
            HttpServletRequest request,
            @PathVariable UUID symptomId,
            @Valid @RequestBody UpdateSymptomRequest body) {

        return ApiResponse.success(
                "Symptôme mis à jour avec succès.",
                service.updateSymptom(
                        currentUser.getMemberId(request),
                        symptomId,
                        body
                )
        );
    }

    @DeleteMapping("/symptoms/{symptomId}")
    public ApiResponse<Void> deleteSymptom(
            HttpServletRequest request,
            @PathVariable UUID symptomId) {

        service.deleteSymptom(
                currentUser.getMemberId(request),
                symptomId
        );

        return ApiResponse.success(
                "Symptôme supprimé avec succès.",
                null
        );
    }

}