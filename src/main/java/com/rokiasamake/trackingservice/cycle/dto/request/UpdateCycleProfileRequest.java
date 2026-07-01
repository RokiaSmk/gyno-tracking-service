package com.rokiasamake.trackingservice.cycle.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record UpdateCycleProfileRequest(

        @NotNull(message = "La durée moyenne du cycle est obligatoire.")
        @Min(15)
        @Max(60)
        Integer averageCycleLength,

        @NotNull(message = "La durée moyenne des règles est obligatoire.")
        @Min(2)
        @Max(15)
        Integer averagePeriodLength,

        @NotNull(message = "Veuillez préciser si votre cycle est régulier.")
        Boolean regularCycle

) {
}