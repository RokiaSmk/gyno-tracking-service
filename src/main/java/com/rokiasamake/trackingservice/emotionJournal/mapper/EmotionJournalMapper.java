package com.rokiasamake.trackingservice.emotionJournal.mapper;

import com.rokiasamake.trackingservice.emotionJournal.dto.response.EmotionJournalResponse;
import com.rokiasamake.trackingservice.emotionJournal.entity.EmotionJournal;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmotionJournalMapper {

    EmotionJournalResponse toResponse(
            EmotionJournal emotionJournal
    );

}