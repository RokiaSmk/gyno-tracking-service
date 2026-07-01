package com.rokiasamake.trackingservice.cycle.dto.request;

import com.rokiasamake.trackingservice.cycle.enums.FlowIntensity;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record FinishCycleRequest(

        LocalDate endDate,

        FlowIntensity flowIntensity

) {
}