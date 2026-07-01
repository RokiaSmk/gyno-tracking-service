package com.rokiasamake.trackingservice.foodJournal.dto.request;

import com.rokiasamake.trackingservice.foodJournal.enums.MealType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UpdateFoodJournalRequest(

        @NotNull(message = "Le type de repas est obligatoire.")
        MealType mealType,

        @NotBlank(message = "La description des aliments est obligatoire.")
        String foodDescription,

        String metabolicReaction,

        @NotNull(message = "La date du repas est obligatoire.")
        LocalDate mealDate

) {
}