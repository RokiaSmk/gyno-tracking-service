package com.rokiasamake.trackingservice.cycle.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CreateCycleProfileRequest(

        @NotNull(message = "La durée moyenne du cycle est obligatoire.")
        @Min(value = 15, message = "La durée minimale est de 15 jours.")
        @Max(value = 60, message = "La durée maximale est de 60 jours.")
        Integer averageCycleLength,

        @NotNull(message = "La durée moyenne des règles est obligatoire.")
        @Min(value = 2, message = "La durée minimale est de 2 jours.")
        @Max(value = 15, message = "La durée maximale est de 15 jours.")
        Integer averagePeriodLength,

        @NotNull(message = "Veuillez préciser si votre cycle est régulier.")
        Boolean regularCycle

) {
}