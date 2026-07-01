package com.rokiasamake.trackingservice.cycle.dto.request;

import com.rokiasamake.trackingservice.cycle.enums.FlowIntensity;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateMenstrualCycleRequest(

        @NotNull(message = "La date de début est obligatoire.")
        LocalDate startDate,

        @NotNull(message = "La date de fin est obligatoire.")
        LocalDate endDate,

        @NotNull(message = "La régularité est obligatoire.")
        Boolean regular,

        @NotNull(message = "L'intensité du flux est obligatoire.")
        FlowIntensity flowIntensity

) {
}