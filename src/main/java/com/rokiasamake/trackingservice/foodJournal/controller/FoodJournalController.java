package com.rokiasamake.trackingservice.foodJournal.controller;

import com.rokiasamake.trackingservice.cycle.dto.response.ApiResponse;
import com.rokiasamake.trackingservice.foodJournal.dto.request.CreateFoodJournalRequest;
import com.rokiasamake.trackingservice.foodJournal.dto.request.UpdateFoodJournalRequest;
import com.rokiasamake.trackingservice.foodJournal.dto.response.FoodJournalResponse;
import com.rokiasamake.trackingservice.foodJournal.service.interfaces.FoodJournalService;
import com.rokiasamake.trackingservice.security.CurrentUser;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(
        name = "🍽️ Journal alimentaire",
        description = "Gestion du journal alimentaire de l'utilisatrice."
)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tracking")
@SecurityRequirement(name = "Bearer Authentication")
public class FoodJournalController {

    private final FoodJournalService service;
    private final CurrentUser currentUser;

    @PostMapping("/food-journal")
    public ApiResponse<FoodJournalResponse> createFoodJournal(
            HttpServletRequest request,
            @Valid @RequestBody CreateFoodJournalRequest body) {

        return ApiResponse.success(
                "Journal alimentaire enregistré avec succès.",
                service.createFoodJournal(
                        currentUser.getMemberId(request),
                        body
                )
        );
    }

    @GetMapping("/cycles/{cycleId}/food-journal")
    public ApiResponse<List<FoodJournalResponse>> getFoodJournals(
            HttpServletRequest request,
            @PathVariable UUID cycleId) {

        return ApiResponse.success(
                "Journal alimentaire récupéré avec succès.",
                service.getFoodJournals(
                        currentUser.getMemberId(request),
                        cycleId
                )
        );
    }

    @PutMapping("/food-journal/{journalId}")
    public ApiResponse<FoodJournalResponse> updateFoodJournal(
            HttpServletRequest request,
            @PathVariable UUID journalId,
            @Valid @RequestBody UpdateFoodJournalRequest body) {

        return ApiResponse.success(
                "Journal alimentaire mis à jour avec succès.",
                service.updateFoodJournal(
                        currentUser.getMemberId(request),
                        journalId,
                        body
                )
        );
    }

    @DeleteMapping("/food-journal/{journalId}")
    public ApiResponse<Void> deleteFoodJournal(
            HttpServletRequest request,
            @PathVariable UUID journalId) {

        service.deleteFoodJournal(
                currentUser.getMemberId(request),
                journalId
        );

        return ApiResponse.success(
                "Journal alimentaire supprimé avec succès.",
                null
        );
    }

    @GetMapping("/food-journal")
    public ApiResponse<List<FoodJournalResponse>> getMemberFoodJournals(
            HttpServletRequest request) {

        return ApiResponse.success(
                "Journal alimentaire récupéré avec succès.",
                service.getMemberFoodJournals(
                        currentUser.getMemberId(request)
                )
        );

    }

}