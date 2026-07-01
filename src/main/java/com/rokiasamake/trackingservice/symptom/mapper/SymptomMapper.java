package com.rokiasamake.trackingservice.symptom.mapper;

import com.rokiasamake.trackingservice.symptom.dto.response.SymptomResponse;
import com.rokiasamake.trackingservice.symptom.entity.Symptom;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SymptomMapper {

    SymptomResponse toResponse(Symptom symptom);

}