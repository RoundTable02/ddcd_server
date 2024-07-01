package com.dadingcoding.web.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity @Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Program extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "program_id")
    private Long id;

    private String title;
    private String description;
    private String programPic;

    @Setter
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "program", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProgramMember> programMembers = new ArrayList<>();

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private ProgramStatus status;

    private String details;

    public void update(String title, String description, String programPic, LocalDateTime startDate, LocalDateTime endDate, ProgramStatus status, String details) {
        this.title = title;
        this.description = description;
        this.programPic = programPic;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.details = details;
    }
}
