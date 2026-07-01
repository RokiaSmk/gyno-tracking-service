package com.rokiasamake.trackingservice.cycle.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record StartCycleRequest(

        LocalDate startDate

) {
}