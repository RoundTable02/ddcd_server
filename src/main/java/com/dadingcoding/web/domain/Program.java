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
    @OneToMany(mappedBy = "program", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProgramMember> programMembers = new ArrayList<>();

    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private ProgramStatus status;

    private String details;
}
