package com.rokiasamake.trackingservice.emotionJournal.dto.request;

import com.rokiasamake.trackingservice.emotionJournal.enums.MoodType;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record CreateEmotionJournalRequest(

        @NotNull(message = "Le cycle est obligatoire.")
        UUID cycleId,

        @NotNull(message = "L'émotion est obligatoire.")
        MoodType mood,

        String note,

        @NotNull(message = "La date est obligatoire.")
        LocalDate emotionDate

) {
}