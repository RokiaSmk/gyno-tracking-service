package com.rokiasamake.trackingservice.cycle.controller;

import com.rokiasamake.trackingservice.cycle.dto.response.ApiResponse;
import com.rokiasamake.trackingservice.cycle.dto.request.FinishCycleRequest;
import com.rokiasamake.trackingservice.cycle.dto.request.StartCycleRequest;
import com.rokiasamake.trackingservice.cycle.dto.response.MenstrualCycleResponse;
import com.rokiasamake.trackingservice.cycle.service.interfaces.MenstrualCycleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.rokiasamake.trackingservice.security.CurrentUser;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
import java.util.UUID;

@Tag(
        name = "🌸 Cycle menstruel",
        description = "Gestion du cycle menstruel de l'utilisatrice."
)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tracking/cycles")
@SecurityRequirement(name = "Bearer Authentication")
public class MenstrualCycleController {

    private final MenstrualCycleService service;
    private final CurrentUser currentUser;

    @PostMapping("/start")
    public ApiResponse<MenstrualCycleResponse> startCycle(
            HttpServletRequest request,
            @RequestBody StartCycleRequest body) {

        return ApiResponse.success(
                "Cycle démarré avec succès.",
                service.startCycle(
                        currentUser.getMemberId(request),
                        body)
        );
    }
    @PatchMapping("/{cycleId}/finish")
    public ApiResponse<MenstrualCycleResponse> finishCycle(
            HttpServletRequest request,
            @PathVariable UUID cycleId,
            @RequestBody FinishCycleRequest body) {

        return ApiResponse.success(
                "Cycle terminé avec succès.",
                service.finishCycle(
                        currentUser.getMemberId(request),
                        cycleId,
                        body)
        );
    }

    @GetMapping
    public ApiResponse<List<MenstrualCycleResponse>> getMyCycles(
            HttpServletRequest request) {

        return ApiResponse.success(
                "Cycles récupérés avec succès.",
                service.getMyCycles(
                        currentUser.getMemberId(request))
        );
    }

    @GetMapping("/{cycleId}")
    public ApiResponse<MenstrualCycleResponse> getCycle(
            HttpServletRequest request,
            @PathVariable UUID cycleId) {

        return ApiResponse.success(
                "Cycle récupéré avec succès.",
                service.getCycle(
                        currentUser.getMemberId(request),
                        cycleId)
        );
    }

    @DeleteMapping("/{cycleId}")
    public ApiResponse<Void> deleteCycle(
            HttpServletRequest request,
            @PathVariable UUID cycleId) {

        service.deleteCycle(
                currentUser.getMemberId(request),
                cycleId);

        return ApiResponse.success(
                "Cycle supprimé avec succès.",
                null
        );
    }

}