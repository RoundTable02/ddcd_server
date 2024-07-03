package com.dadingcoding.web.domain;

import com.dadingcoding.web.service.StringListConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity @Builder @Getter
@AllArgsConstructor @NoArgsConstructor
public class Schedule extends PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mentee_id")
    private Member mentee;

    @Convert(converter = StringListConverter.class)
    private List<String> scheduleTime; // LocalDateTime -> String 으로 저장

    private String sessionNumber;
}
