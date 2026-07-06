package com.rokiasamake.trackingservice.foodJournal.dto.request;

import com.rokiasamake.trackingservice.foodJournal.enums.MealType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record UpdateFoodJournalRequest(

        @NotNull(message = "Le type de repas est obligatoire.")
        MealType mealType,

        @NotEmpty(message = "Sélectionnez au moins un aliment.")
        List<UUID> foodIds,

        @NotNull(message = "La date du repas est obligatoire.")
        LocalDate mealDate

) {
}