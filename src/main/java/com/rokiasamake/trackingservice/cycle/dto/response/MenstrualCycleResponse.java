package com.rokiasamake.trackingservice.cycle.dto.response;

import com.rokiasamake.trackingservice.cycle.enums.CycleStatus;
import com.rokiasamake.trackingservice.cycle.enums.FlowIntensity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record MenstrualCycleResponse(

        UUID id,

        LocalDate startDate,

        LocalDate endDate,

        Integer actualPeriodLength,

        FlowIntensity flowIntensity,

        CycleStatus status,

        LocalDateTime createdAt,

        LocalDateTime updatedAt

) {
}