package com.rokiasamake.trackingservice.symptom.dto.response;

import com.rokiasamake.trackingservice.symptom.enums.SymptomIntensity;
import com.rokiasamake.trackingservice.symptom.enums.SymptomType;

import java.time.LocalDateTime;
import java.util.UUID;

public record SymptomResponse(

        UUID id,

        UUID cycleId,

        SymptomType symptomType,

        SymptomIntensity intensity,

        String note,

        LocalDateTime createdAt,

        LocalDateTime updatedAt

) {
}