package com.rokiasamake.trackingservice.cycle.service.interfaces;

import com.rokiasamake.trackingservice.cycle.dto.request.CreateCycleProfileRequest;
import com.rokiasamake.trackingservice.cycle.dto.request.UpdateCycleProfileRequest;
import com.rokiasamake.trackingservice.cycle.dto.response.CycleProfileResponse;

import java.util.UUID;

public interface CycleProfileService {

    CycleProfileResponse createProfile(
            UUID memberId,
            CreateCycleProfileRequest request
    );

    CycleProfileResponse getProfile(
            UUID memberId
    );

    CycleProfileResponse updateProfile(
            UUID memberId,
            UpdateCycleProfileRequest request
    );

}