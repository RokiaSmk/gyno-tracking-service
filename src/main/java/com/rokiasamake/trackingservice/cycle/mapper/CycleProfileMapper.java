package com.rokiasamake.trackingservice.cycle.mapper;

import com.rokiasamake.trackingservice.cycle.dto.request.CreateCycleProfileRequest;
import com.rokiasamake.trackingservice.cycle.dto.response.CycleProfileResponse;
import com.rokiasamake.trackingservice.cycle.entity.CycleProfile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CycleProfileMapper {

    CycleProfile toEntity(CreateCycleProfileRequest request);

    CycleProfileResponse toResponse(CycleProfile profile);

}