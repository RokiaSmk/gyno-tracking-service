package com.rokiasamake.trackingservice.cycle.repository;

import com.rokiasamake.trackingservice.cycle.entity.MenstrualCycle;
import com.rokiasamake.trackingservice.cycle.enums.CycleStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MenstrualCycleRepository extends JpaRepository<MenstrualCycle, UUID> {

    Optional<MenstrualCycle> findByIdAndDeletedAtIsNull(
            UUID id
    );

    Optional<MenstrualCycle> findFirstByMemberIdAndStatusOrderByStartDateDesc(
            UUID memberId,
            CycleStatus status
    );



    Optional<MenstrualCycle> findByIdAndMemberIdAndDeletedAtIsNull(
            UUID id,
            UUID memberId
    );

    List<MenstrualCycle> findByMemberIdAndDeletedAtIsNullOrderByStartDateDesc(
            UUID memberId
    );

    boolean existsByMemberIdAndStatusAndDeletedAtIsNull(
            UUID memberId,
            CycleStatus status
    );

    long countByDeletedAtIsNull();
}