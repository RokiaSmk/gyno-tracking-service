package com.rokiasamake.trackingservice.cycle.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record CycleProfileResponse(

        UUID memberId,

        Integer averageCycleLength,

        Integer averagePeriodLength,

        Boolean regularCycle,

        LocalDateTime createdAt,

        LocalDateTime updatedAt

) {
}
