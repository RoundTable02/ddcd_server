package com.dadingcoding.web.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity @Getter
public class ScheduleTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_time_id")
    private Long id;

    private LocalDateTime availableDateTime;
}
