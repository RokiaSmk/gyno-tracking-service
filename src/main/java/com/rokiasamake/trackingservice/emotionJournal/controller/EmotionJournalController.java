package com.rokiasamake.trackingservice.emotionJournal.controller;

import com.rokiasamake.trackingservice.cycle.dto.response.ApiResponse;
import com.rokiasamake.trackingservice.emotionJournal.dto.request.CreateEmotionJournalRequest;
import com.rokiasamake.trackingservice.emotionJournal.dto.request.UpdateEmotionJournalRequest;
import com.rokiasamake.trackingservice.emotionJournal.dto.response.EmotionJournalResponse;
import com.rokiasamake.trackingservice.emotionJournal.service.interfaces.EmotionJournalService;
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
        name = "😊 Journal émotionnel",
        description = "Gestion du journal émotionnel de l'utilisatrice."
)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/tracking")
@SecurityRequirement(name = "Bearer Authentication")
public class EmotionJournalController {

    private final EmotionJournalService service;
    private final CurrentUser currentUser;

    @PostMapping("/emotion-journal")
    public ApiResponse<EmotionJournalResponse> createEmotionJournal(
            HttpServletRequest request,
            @Valid @RequestBody CreateEmotionJournalRequest body) {

        return ApiResponse.success(
                "Journal émotionnel enregistré avec succès.",
                service.createEmotionJournal(
                        currentUser.getMemberId(request),
                        body
                )
        );
    }

    @GetMapping("/cycles/{cycleId}/emotion-journal")
    public ApiResponse<List<EmotionJournalResponse>> getEmotionJournals(
            HttpServletRequest request,
            @PathVariable UUID cycleId) {

        return ApiResponse.success(
                "Journal émotionnel récupéré avec succès.",
                service.getEmotionJournals(
                        currentUser.getMemberId(request),
                        cycleId
                )
        );
    }

    @PutMapping("/emotion-journal/{journalId}")
    public ApiResponse<EmotionJournalResponse> updateEmotionJournal(
            HttpServletRequest request,
            @PathVariable UUID journalId,
            @Valid @RequestBody UpdateEmotionJournalRequest body) {

        return ApiResponse.success(
                "Journal émotionnel mis à jour avec succès.",
                service.updateEmotionJournal(
                        currentUser.getMemberId(request),
                        journalId,
                        body
                )
        );
    }

    @DeleteMapping("/emotion-journal/{journalId}")
    public ApiResponse<Void> deleteEmotionJournal(
            HttpServletRequest request,
            @PathVariable UUID journalId) {

        service.deleteEmotionJournal(
                currentUser.getMemberId(request),
                journalId
        );

        return ApiResponse.success(
                "Journal émotionnel supprimé avec succès.",
                null
        );
    }
}