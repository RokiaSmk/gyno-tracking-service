package com.rokiasamake.trackingservice.cycle.service.interfaces;

import com.rokiasamake.trackingservice.cycle.dto.request.FinishCycleRequest;
import com.rokiasamake.trackingservice.cycle.dto.request.StartCycleRequest;
import com.rokiasamake.trackingservice.cycle.dto.response.MenstrualCycleResponse;

import java.util.List;
import java.util.UUID;

public interface MenstrualCycleService {

    MenstrualCycleResponse startCycle(
            UUID memberId,
            StartCycleRequest request
    );

    MenstrualCycleResponse finishCycle(
            UUID memberId,
            UUID cycleId,
            FinishCycleRequest request
    );

    List<MenstrualCycleResponse> getMyCycles(
            UUID memberId
    );

    MenstrualCycleResponse getCycle(
            UUID memberId,
            UUID cycleId
    );

    void deleteCycle(
            UUID memberId,
            UUID cycleId
    );

}