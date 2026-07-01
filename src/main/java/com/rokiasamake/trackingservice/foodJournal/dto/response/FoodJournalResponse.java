package com.rokiasamake.trackingservice.foodJournal.dto.response;

import com.rokiasamake.trackingservice.foodJournal.enums.MealType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record FoodJournalResponse(

        UUID id,

        UUID cycleId,

        MealType mealType,

        String foodDescription,

        String metabolicReaction,

        LocalDate mealDate,

        LocalDateTime createdAt,

        LocalDateTime updatedAt

) {
}