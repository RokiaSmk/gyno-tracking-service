package com.rokiasamake.trackingservice.emotionJournal.service.impl;

import com.rokiasamake.trackingservice.cycle.entity.MenstrualCycle;
import com.rokiasamake.trackingservice.cycle.repository.MenstrualCycleRepository;
import com.rokiasamake.trackingservice.emotionJournal.dto.request.CreateEmotionJournalRequest;
import com.rokiasamake.trackingservice.emotionJournal.dto.request.UpdateEmotionJournalRequest;
import com.rokiasamake.trackingservice.emotionJournal.dto.response.EmotionJournalResponse;
import com.rokiasamake.trackingservice.emotionJournal.entity.EmotionJournal;
import com.rokiasamake.trackingservice.emotionJournal.exception.EmotionJournalNotFoundException;
import com.rokiasamake.trackingservice.emotionJournal.mapper.EmotionJournalMapper;
import com.rokiasamake.trackingservice.emotionJournal.repository.EmotionJournalRepository;
import com.rokiasamake.trackingservice.emotionJournal.service.interfaces.EmotionJournalService;
import com.rokiasamake.trackingservice.symptom.exception.CycleNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmotionJournalServiceImpl implements EmotionJournalService {

    private final EmotionJournalRepository repository;
    private final MenstrualCycleRepository cycleRepository;
    private final EmotionJournalMapper mapper;

    @Override
    public EmotionJournalResponse createEmotionJournal(
            UUID memberId,
            CreateEmotionJournalRequest request) {

        MenstrualCycle cycle = cycleRepository
                .findByIdAndMemberIdAndDeletedAtIsNull(
                        request.cycleId(),
                        memberId)
                .orElseThrow(() ->
                        new CycleNotFoundException(
                                "Cycle introuvable."
                        ));

        EmotionJournal journal = EmotionJournal.builder()
                .memberId(memberId)
                .cycleId(cycle.getId())
                .mood(request.mood())
                .note(request.note())
                .emotionDate(request.emotionDate())
                .build();

        repository.save(journal);

        return mapper.toResponse(journal);
    }

    @Override
    public List<EmotionJournalResponse> getEmotionJournals(
            UUID memberId,
            UUID cycleId) {

        return repository
                .findByMemberIdAndCycleIdAndDeletedAtIsNullOrderByEmotionDateDesc(
                        memberId,
                        cycleId
                )
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Override
    public EmotionJournalResponse updateEmotionJournal(
            UUID memberId,
            UUID journalId,
            UpdateEmotionJournalRequest request) {

        EmotionJournal journal = repository
                .findByIdAndMemberIdAndDeletedAtIsNull(
                        journalId,
                        memberId)
                .orElseThrow(() ->
                        new EmotionJournalNotFoundException(
                                "Journal émotionnel introuvable."
                        ));

        journal.setMood(request.mood());
        journal.setNote(request.note());
        journal.setEmotionDate(request.emotionDate());

        repository.save(journal);

        return mapper.toResponse(journal);
    }

    @Override
    public void deleteEmotionJournal(
            UUID memberId,
            UUID journalId) {

        EmotionJournal journal = repository
                .findByIdAndMemberIdAndDeletedAtIsNull(
                        journalId,
                        memberId)
                .orElseThrow(() ->
                        new EmotionJournalNotFoundException(
                                "Journal émotionnel introuvable."
                        ));

        journal.setDeletedAt(LocalDateTime.now());

        repository.save(journal);
    }
    @Override
    public List<EmotionJournalResponse> getMemberEmotionJournals(
            UUID memberId) {

        return repository
                .findByMemberIdAndDeletedAtIsNullOrderByEmotionDateDesc(
                        memberId
                )
                .stream()
                .map(mapper::toResponse)
                .toList();

    }
}