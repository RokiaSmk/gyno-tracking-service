package com.rokiasamake.trackingservice.foodJournal.entity;

import com.rokiasamake.trackingservice.cycle.entity.BaseEntity;
import com.rokiasamake.trackingservice.foodJournal.enums.MealType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "food_journals")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class FoodJournal extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private UUID memberId;

    @Column(nullable = false)
    private UUID cycleId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MealType mealType;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String foodDescription;

    @Column(columnDefinition = "TEXT")
    private String metabolicReaction;

    @Column(nullable = false)
    private LocalDate mealDate;

    @Column
    private LocalDateTime deletedAt;

}