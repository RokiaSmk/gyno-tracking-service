package com.rokiasamake.trackingservice.cycle.entity;

import com.rokiasamake.trackingservice.cycle.enums.CycleStatus;
import com.rokiasamake.trackingservice.cycle.enums.FlowIntensity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "menstrual_cycles")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MenstrualCycle extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID memberId;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column
    private LocalDate endDate;

    @Column
    private Integer actualPeriodLength;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private FlowIntensity flowIntensity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private CycleStatus status = CycleStatus.ONGOING;

    @Column
    private LocalDateTime deletedAt;

}