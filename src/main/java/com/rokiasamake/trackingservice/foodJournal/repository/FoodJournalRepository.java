package com.rokiasamake.trackingservice.foodJournal.repository;

import com.rokiasamake.trackingservice.foodJournal.entity.FoodJournal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FoodJournalRepository
        extends JpaRepository<FoodJournal, UUID> {

    Optional<FoodJournal> findByIdAndMemberIdAndDeletedAtIsNull(
            UUID id,
            UUID memberId
    );

    List<FoodJournal> findByMemberIdAndCycleIdAndDeletedAtIsNullOrderByMealDateDesc(
            UUID memberId,
            UUID cycleId
    );
    List<FoodJournal> findByMemberIdAndDeletedAtIsNullOrderByMealDateDesc(
            UUID memberId
    );
}