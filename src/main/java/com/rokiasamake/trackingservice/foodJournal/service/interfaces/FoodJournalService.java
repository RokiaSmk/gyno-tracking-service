package com.rokiasamake.trackingservice.foodJournal.service.interfaces;

import com.rokiasamake.trackingservice.foodJournal.dto.request.CreateFoodJournalRequest;
import com.rokiasamake.trackingservice.foodJournal.dto.request.UpdateFoodJournalRequest;
import com.rokiasamake.trackingservice.foodJournal.dto.response.FoodJournalResponse;

import java.util.List;
import java.util.UUID;

public interface FoodJournalService {

    FoodJournalResponse createFoodJournal(
            UUID memberId,
            CreateFoodJournalRequest request
    );

    List<FoodJournalResponse> getFoodJournals(
            UUID memberId,
            UUID cycleId
    );

    FoodJournalResponse updateFoodJournal(
            UUID memberId,
            UUID journalId,
            UpdateFoodJournalRequest request
    );

    void deleteFoodJournal(
            UUID memberId,
            UUID journalId
    );

    List<FoodJournalResponse> getMemberFoodJournals(
            UUID memberId
    );

}