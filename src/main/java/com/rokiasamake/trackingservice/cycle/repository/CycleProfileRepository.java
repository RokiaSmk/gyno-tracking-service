package com.rokiasamake.trackingservice.cycle.repository;

import com.rokiasamake.trackingservice.cycle.entity.CycleProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CycleProfileRepository extends JpaRepository<CycleProfile, UUID> {

    Optional<CycleProfile> findByMemberId(UUID memberId);

    boolean existsByMemberId(UUID memberId);

}