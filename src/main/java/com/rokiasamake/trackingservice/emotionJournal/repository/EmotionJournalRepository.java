package com.rokiasamake.trackingservice.emotionJournal.repository;

import com.rokiasamake.trackingservice.emotionJournal.entity.EmotionJournal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmotionJournalRepository
        extends JpaRepository<EmotionJournal, UUID> {

    Optional<EmotionJournal> findByIdAndMemberIdAndDeletedAtIsNull(
            UUID id,
            UUID memberId
    );

    List<EmotionJournal> findByMemberIdAndCycleIdAndDeletedAtIsNullOrderByEmotionDateDesc(
            UUID memberId,
            UUID cycleId
    );
    List<EmotionJournal> findByMemberIdAndDeletedAtIsNullOrderByEmotionDateDesc(
            UUID memberId
    );
}