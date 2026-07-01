package com.rokiasamake.trackingservice.symptom.entity;

import com.rokiasamake.trackingservice.cycle.entity.BaseEntity;
import com.rokiasamake.trackingservice.symptom.enums.SymptomIntensity;
import com.rokiasamake.trackingservice.symptom.enums.SymptomType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "symptoms")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Symptom extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID memberId;

    @Column(nullable = false)
    private UUID cycleId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SymptomType symptomType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SymptomIntensity intensity;

    @Column(length = 500)
    private String note;

    @Column
    private LocalDateTime deletedAt;

}