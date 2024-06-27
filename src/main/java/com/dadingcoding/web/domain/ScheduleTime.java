package com.dadingcoding.web.domain;

import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
public class ScheduleTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_time_id")
    private Long id;

    private LocalTime availableTime;
}
