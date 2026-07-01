package com.rokiasamake.trackingservice.foodJournal.mapper;

import com.rokiasamake.trackingservice.foodJournal.dto.response.FoodJournalResponse;
import com.rokiasamake.trackingservice.foodJournal.entity.FoodJournal;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FoodJournalMapper {

    FoodJournalResponse toResponse(
            FoodJournal foodJournal
    );

}