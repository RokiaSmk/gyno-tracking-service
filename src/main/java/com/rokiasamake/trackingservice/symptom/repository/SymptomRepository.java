package com.rokiasamake.trackingservice.symptom.repository;

import com.rokiasamake.trackingservice.symptom.entity.Symptom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SymptomRepository extends JpaRepository<Symptom, UUID> {

    List<Symptom> findByMemberIdAndCycleIdAndDeletedAtIsNullOrderByCreatedAtDesc(
            UUID memberId,
            UUID cycleId
    );

    Optional<Symptom> findByIdAndMemberIdAndDeletedAtIsNull(
            UUID id,
            UUID memberId
    );

}