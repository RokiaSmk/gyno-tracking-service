package com.rokiasamake.trackingservice.cycle.service.impl;

import com.rokiasamake.trackingservice.cycle.dto.request.CreateCycleProfileRequest;
import com.rokiasamake.trackingservice.cycle.dto.request.UpdateCycleProfileRequest;
import com.rokiasamake.trackingservice.cycle.dto.response.CycleProfileResponse;
import com.rokiasamake.trackingservice.cycle.entity.CycleProfile;
import com.rokiasamake.trackingservice.cycle.exception.CycleProfileAlreadyExistsException;
import com.rokiasamake.trackingservice.cycle.exception.CycleProfileNotFoundException;
import com.rokiasamake.trackingservice.cycle.mapper.CycleProfileMapper;
import com.rokiasamake.trackingservice.cycle.repository.CycleProfileRepository;
import com.rokiasamake.trackingservice.cycle.service.interfaces.CycleProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CycleProfileServiceImpl implements CycleProfileService {

    private final CycleProfileRepository repository;
    private final CycleProfileMapper mapper;

    @Override
    public CycleProfileResponse createProfile(
            UUID memberId,
            CreateCycleProfileRequest request) {

        if (repository.existsByMemberId(memberId)) {
            throw new CycleProfileAlreadyExistsException(
                    "Votre profil menstruel existe déjà."
            );
        }

        CycleProfile profile = mapper.toEntity(request);

        profile.setMemberId(memberId);

        repository.save(profile);

        return mapper.toResponse(profile);
    }

    @Override
    public CycleProfileResponse getProfile(UUID memberId) {

        CycleProfile profile = repository
                .findByMemberId(memberId)
                .orElseThrow(() ->
                        new CycleProfileNotFoundException(
                                "Profil menstruel introuvable."
                        ));

        return mapper.toResponse(profile);
    }

    @Override
    public CycleProfileResponse updateProfile(
            UUID memberId,
            UpdateCycleProfileRequest request) {

        CycleProfile profile = repository
                .findByMemberId(memberId)
                .orElseThrow(() ->
                        new CycleProfileNotFoundException(
                                "Profil menstruel introuvable."
                        ));

        profile.setAverageCycleLength(
                request.averageCycleLength()
        );

        profile.setAveragePeriodLength(
                request.averagePeriodLength()
        );

        profile.setRegularCycle(
                request.regularCycle()
        );

        repository.save(profile);

        return mapper.toResponse(profile);
    }

}