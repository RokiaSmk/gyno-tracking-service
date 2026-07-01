package com.rokiasamake.trackingservice.symptom.service.interfaces;

import com.rokiasamake.trackingservice.symptom.dto.request.CreateSymptomRequest;
import com.rokiasamake.trackingservice.symptom.dto.request.UpdateSymptomRequest;
import com.rokiasamake.trackingservice.symptom.dto.response.SymptomResponse;

import java.util.List;
import java.util.UUID;

public interface SymptomService {

    SymptomResponse createSymptom(
            UUID memberId,
            CreateSymptomRequest request
    );

    List<SymptomResponse> getCycleSymptoms(
            UUID memberId,
            UUID cycleId
    );

    SymptomResponse updateSymptom(
            UUID memberId,
            UUID symptomId,
            UpdateSymptomRequest request
    );

    void deleteSymptom(
            UUID memberId,
            UUID symptomId
    );

}