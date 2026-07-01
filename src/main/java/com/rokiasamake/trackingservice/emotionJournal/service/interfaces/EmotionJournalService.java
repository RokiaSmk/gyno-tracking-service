package com.rokiasamake.trackingservice.emotionJournal.service.interfaces;

import com.rokiasamake.trackingservice.emotionJournal.dto.request.CreateEmotionJournalRequest;
import com.rokiasamake.trackingservice.emotionJournal.dto.request.UpdateEmotionJournalRequest;
import com.rokiasamake.trackingservice.emotionJournal.dto.response.EmotionJournalResponse;

import java.util.List;
import java.util.UUID;

public interface EmotionJournalService {

    EmotionJournalResponse createEmotionJournal(
            UUID memberId,
            CreateEmotionJournalRequest request
    );

    List<EmotionJournalResponse> getEmotionJournals(
            UUID memberId,
            UUID cycleId
    );

    EmotionJournalResponse updateEmotionJournal(
            UUID memberId,
            UUID journalId,
            UpdateEmotionJournalRequest request
    );

    void deleteEmotionJournal(
            UUID memberId,
            UUID journalId
    );

}