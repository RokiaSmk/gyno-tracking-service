package com.rokiasamake.trackingservice.cycle.mapper;

import com.rokiasamake.trackingservice.cycle.dto.response.MenstrualCycleResponse;
import com.rokiasamake.trackingservice.cycle.entity.MenstrualCycle;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MenstrualCycleMapper {

    MenstrualCycleResponse toResponse(
            MenstrualCycle cycle
    );

}