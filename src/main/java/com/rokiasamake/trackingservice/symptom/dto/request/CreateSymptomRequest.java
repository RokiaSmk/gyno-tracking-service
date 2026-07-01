package com.rokiasamake.trackingservice.symptom.dto.request;

import com.rokiasamake.trackingservice.symptom.enums.SymptomIntensity;
import com.rokiasamake.trackingservice.symptom.enums.SymptomType;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateSymptomRequest(

        @NotNull
        UUID cycleId,

        @NotNull
        SymptomType symptomType,

        @NotNull
        SymptomIntensity intensity,

        String note

) {
}