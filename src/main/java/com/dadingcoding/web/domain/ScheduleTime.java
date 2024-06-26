package com.dadingcoding.web.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalTime;

@Entity
public class ScheduleTime {

    @Id
    @GeneratedValue
    @Column(name = "schedule_time_id")
    private Long id;

    private LocalTime availableTime;
}
