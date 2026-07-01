package com.rokiasamake.trackingservice.cycle.service.impl;

import com.rokiasamake.trackingservice.cycle.dto.request.FinishCycleRequest;
import com.rokiasamake.trackingservice.cycle.dto.request.StartCycleRequest;
import com.rokiasamake.trackingservice.cycle.dto.response.MenstrualCycleResponse;
import com.rokiasamake.trackingservice.cycle.entity.MenstrualCycle;
import com.rokiasamake.trackingservice.cycle.enums.CycleStatus;
import com.rokiasamake.trackingservice.cycle.exception.*;
import com.rokiasamake.trackingservice.cycle.mapper.MenstrualCycleMapper;
import com.rokiasamake.trackingservice.cycle.repository.CycleProfileRepository;
import com.rokiasamake.trackingservice.cycle.repository.MenstrualCycleRepository;
import com.rokiasamake.trackingservice.cycle.service.interfaces.MenstrualCycleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MenstrualCycleServiceImpl implements MenstrualCycleService {

    private final MenstrualCycleRepository repository;
    private final CycleProfileRepository cycleProfileRepository;
    private final MenstrualCycleMapper mapper;

    @Override
    public MenstrualCycleResponse startCycle(
            UUID memberId,
            StartCycleRequest request) {

        if (!cycleProfileRepository.existsByMemberId(memberId)) {
            throw new CycleProfileNotFoundException(
                    "Veuillez d'abord créer votre profil menstruel."
            );
        }

        if (repository.existsByMemberIdAndStatusAndDeletedAtIsNull(
                memberId,
                CycleStatus.ONGOING)) {

            throw new CycleAlreadyOngoingException(
                    "Vous avez déjà un cycle en cours."
            );
        }

        LocalDate startDate = request.startDate() != null
                ? request.startDate()
                : LocalDate.now();

        if (startDate.isAfter(LocalDate.now())) {
            throw new InvalidCycleDatesException(
                    "La date de début ne peut pas être dans le futur."
            );
        }

        MenstrualCycle cycle = MenstrualCycle.builder()
                .memberId(memberId)
                .startDate(startDate)
                .status(CycleStatus.ONGOING)
                .build();

        repository.save(cycle);

        return mapper.toResponse(cycle);
    }

    @Override
    public MenstrualCycleResponse finishCycle(
            UUID memberId,
            UUID cycleId,
            FinishCycleRequest request) {

        MenstrualCycle cycle = repository
                .findByIdAndMemberIdAndDeletedAtIsNull(cycleId, memberId)
                .orElseThrow(() ->
                        new MenstrualCycleNotFoundException(
                                "Cycle introuvable."
                        ));

        if (cycle.getStatus() == CycleStatus.COMPLETED) {
            throw new CycleAlreadyCompletedException(
                    "Ce cycle est déjà terminé."
            );
        }

        LocalDate endDate = request.endDate() != null
                ? request.endDate()
                : LocalDate.now();

        if (endDate.isAfter(LocalDate.now())) {
            throw new InvalidCycleDatesException(
                    "La date de fin ne peut pas être dans le futur."
            );
        }

        if (endDate.isBefore(cycle.getStartDate())) {
            throw new InvalidCycleDatesException(
                    "La date de fin ne peut pas être antérieure à la date de début."
            );
        }

        cycle.setEndDate(endDate);

        cycle.setActualPeriodLength(
                (int) ChronoUnit.DAYS.between(
                        cycle.getStartDate(),
                        endDate
                ) + 1
        );

        cycle.setFlowIntensity(request.flowIntensity());

        cycle.setStatus(CycleStatus.COMPLETED);

        repository.save(cycle);

        // TODO : Publish CycleCompletedEvent to Kafka

        return mapper.toResponse(cycle);
    }

    @Override
    public List<MenstrualCycleResponse> getMyCycles(UUID memberId) {

        return repository
                .findByMemberIdAndDeletedAtIsNullOrderByStartDateDesc(memberId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public MenstrualCycleResponse getCycle(
            UUID memberId,
            UUID cycleId) {

        MenstrualCycle cycle = repository
                .findByIdAndMemberIdAndDeletedAtIsNull(cycleId, memberId)
                .orElseThrow(() ->
                        new MenstrualCycleNotFoundException(
                                "Cycle introuvable."
                        ));

        return mapper.toResponse(cycle);
    }

    @Override
    public void deleteCycle(
            UUID memberId,
            UUID cycleId) {

        MenstrualCycle cycle = repository
                .findByIdAndMemberIdAndDeletedAtIsNull(cycleId, memberId)
                .orElseThrow(() ->
                        new MenstrualCycleNotFoundException(
                                "Cycle introuvable."
                        ));

        cycle.setDeletedAt(LocalDateTime.now());

        repository.save(cycle);
    }

}