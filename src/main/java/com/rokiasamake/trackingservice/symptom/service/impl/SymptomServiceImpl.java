package com.rokiasamake.trackingservice.symptom.service.impl;

import com.rokiasamake.trackingservice.cycle.entity.MenstrualCycle;
import com.rokiasamake.trackingservice.cycle.repository.MenstrualCycleRepository;
import com.rokiasamake.trackingservice.symptom.dto.request.CreateSymptomRequest;
import com.rokiasamake.trackingservice.symptom.dto.request.UpdateSymptomRequest;
import com.rokiasamake.trackingservice.symptom.dto.response.SymptomResponse;
import com.rokiasamake.trackingservice.symptom.entity.Symptom;
import com.rokiasamake.trackingservice.symptom.exception.CycleNotFoundException;
import com.rokiasamake.trackingservice.symptom.exception.SymptomNotFoundException;
import com.rokiasamake.trackingservice.symptom.mapper.SymptomMapper;
import com.rokiasamake.trackingservice.symptom.repository.SymptomRepository;
import com.rokiasamake.trackingservice.symptom.service.interfaces.SymptomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SymptomServiceImpl implements SymptomService {

    private final SymptomRepository repository;
    private final MenstrualCycleRepository cycleRepository;
    private final SymptomMapper mapper;

    @Override
    public SymptomResponse createSymptom(
            UUID memberId,
            CreateSymptomRequest request) {

        MenstrualCycle cycle = cycleRepository
                .findByIdAndMemberIdAndDeletedAtIsNull(
                        request.cycleId(),
                        memberId)
                .orElseThrow(() ->
                        new CycleNotFoundException(
                                "Cycle introuvable."
                        ));

        Symptom symptom = Symptom.builder()
                .memberId(memberId)
                .cycleId(cycle.getId())
                .symptomType(request.symptomType())
                .intensity(request.intensity())
                .note(request.note())
                .build();

        repository.save(symptom);

        return mapper.toResponse(symptom);
    }

    @Override
    public List<SymptomResponse> getCycleSymptoms(
            UUID memberId,
            UUID cycleId) {

        return repository
                .findByMemberIdAndCycleIdAndDeletedAtIsNullOrderByCreatedAtDesc(
                        memberId,
                        cycleId
                )
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public SymptomResponse updateSymptom(
            UUID memberId,
            UUID symptomId,
            UpdateSymptomRequest request) {

        Symptom symptom = repository
                .findByIdAndMemberIdAndDeletedAtIsNull(
                        symptomId,
                        memberId)
                .orElseThrow(() ->
                        new SymptomNotFoundException(
                                "Symptôme introuvable."
                        ));

        symptom.setSymptomType(request.symptomType());
        symptom.setIntensity(request.intensity());
        symptom.setNote(request.note());

        repository.save(symptom);

        return mapper.toResponse(symptom);
    }

    @Override
    public void deleteSymptom(
            UUID memberId,
            UUID symptomId) {

        Symptom symptom = repository
                .findByIdAndMemberIdAndDeletedAtIsNull(
                        symptomId,
                        memberId)
                .orElseThrow(() ->
                        new SymptomNotFoundException(
                                "Symptôme introuvable."
                        ));

        symptom.setDeletedAt(LocalDateTime.now());

        repository.save(symptom);
    }
    @Override
    public List<SymptomResponse> getMemberSymptoms(
            UUID memberId) {

        return repository
                .findByMemberIdAndDeletedAtIsNullOrderByCreatedAtDesc(
                        memberId
                )
                .stream()
                .map(mapper::toResponse)
                .toList();

    }

}