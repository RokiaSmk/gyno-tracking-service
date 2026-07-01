package com.rokiasamake.trackingservice.emotionJournal.dto.request;

import com.rokiasamake.trackingservice.emotionJournal.enums.MoodType;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UpdateEmotionJournalRequest(

        @NotNull(message = "L'émotion est obligatoire.")
        MoodType mood,

        String note,

        @NotNull(message = "La date est obligatoire.")
        LocalDate emotionDate

) {
}