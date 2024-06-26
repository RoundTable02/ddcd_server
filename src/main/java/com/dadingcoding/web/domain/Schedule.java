package com.dadingcoding.web.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Schedule extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name = "schedule_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentor_id")
    private Member mentor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentee_id")
    private Member mentee;

    @OneToMany
    @JoinColumn(name = "schedule_time_list")
    private List<ScheduleTime> scheduleTimeList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private ScheduleType scheduleType;

    private String title;

    private String content;


}
