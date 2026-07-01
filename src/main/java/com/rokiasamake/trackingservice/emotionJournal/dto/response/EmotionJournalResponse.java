package com.rokiasamake.trackingservice.emotionJournal.dto.response;

import com.rokiasamake.trackingservice.emotionJournal.enums.MoodType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record EmotionJournalResponse(

        UUID id,

        UUID cycleId,

        MoodType mood,

        String note,

        LocalDate emotionDate,

        LocalDateTime createdAt,

        LocalDateTime updatedAt

) {
}