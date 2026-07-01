package com.rokiasamake.trackingservice.cycle.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "cycle_profiles")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CycleProfile extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private UUID memberId;

    @Column(nullable = false)
    private Integer averageCycleLength;

    @Column(nullable = false)
    private Integer averagePeriodLength;

    @Column(nullable = false)
    private Boolean regularCycle;

}