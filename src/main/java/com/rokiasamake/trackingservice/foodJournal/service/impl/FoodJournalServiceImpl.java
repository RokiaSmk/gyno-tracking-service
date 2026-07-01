package com.rokiasamake.trackingservice.foodJournal.service.impl;

import com.rokiasamake.trackingservice.cycle.entity.MenstrualCycle;
import com.rokiasamake.trackingservice.cycle.repository.MenstrualCycleRepository;
import com.rokiasamake.trackingservice.foodJournal.dto.request.CreateFoodJournalRequest;
import com.rokiasamake.trackingservice.foodJournal.dto.request.UpdateFoodJournalRequest;
import com.rokiasamake.trackingservice.foodJournal.dto.response.FoodJournalResponse;
import com.rokiasamake.trackingservice.foodJournal.entity.FoodJournal;
import com.rokiasamake.trackingservice.foodJournal.exception.FoodJournalNotFoundException;
import com.rokiasamake.trackingservice.foodJournal.mapper.FoodJournalMapper;
import com.rokiasamake.trackingservice.foodJournal.repository.FoodJournalRepository;
import com.rokiasamake.trackingservice.foodJournal.service.interfaces.FoodJournalService;
import com.rokiasamake.trackingservice.symptom.exception.CycleNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FoodJournalServiceImpl implements FoodJournalService {

    private final FoodJournalRepository repository;
    private final MenstrualCycleRepository cycleRepository;
    private final FoodJournalMapper mapper;

    @Override
    public FoodJournalResponse createFoodJournal(
            UUID memberId,
            CreateFoodJournalRequest request) {

        MenstrualCycle cycle = cycleRepository
                .findByIdAndMemberIdAndDeletedAtIsNull(
                        request.cycleId(),
                        memberId)
                .orElseThrow(() ->
                        new CycleNotFoundException(
                                "Cycle introuvable."
                        ));

        FoodJournal journal = FoodJournal.builder()
                .memberId(memberId)
                .cycleId(cycle.getId())
                .mealType(request.mealType())
                .foodDescription(request.foodDescription())
                .metabolicReaction(request.metabolicReaction())
                .mealDate(request.mealDate())
                .build();

        repository.save(journal);

        return mapper.toResponse(journal);
    }

    @Override
    public List<FoodJournalResponse> getFoodJournals(
            UUID memberId,
            UUID cycleId) {

        return repository
                .findByMemberIdAndCycleIdAndDeletedAtIsNullOrderByMealDateDesc(
                        memberId,
                        cycleId
                )
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public FoodJournalResponse updateFoodJournal(
            UUID memberId,
            UUID journalId,
            UpdateFoodJournalRequest request) {

        FoodJournal journal = repository
                .findByIdAndMemberIdAndDeletedAtIsNull(
                        journalId,
                        memberId)
                .orElseThrow(() ->
                        new FoodJournalNotFoundException(
                                "Journal alimentaire introuvable."
                        ));

        journal.setMealType(request.mealType());
        journal.setFoodDescription(request.foodDescription());
        journal.setMetabolicReaction(request.metabolicReaction());
        journal.setMealDate(request.mealDate());

        repository.save(journal);

        return mapper.toResponse(journal);
    }

    @Override
    public void deleteFoodJournal(
            UUID memberId,
            UUID journalId) {

        FoodJournal journal = repository
                .findByIdAndMemberIdAndDeletedAtIsNull(
                        journalId,
                        memberId)
                .orElseThrow(() ->
                        new FoodJournalNotFoundException(
                                "Journal alimentaire introuvable."
                        ));

        journal.setDeletedAt(LocalDateTime.now());

        repository.save(journal);
    }

}