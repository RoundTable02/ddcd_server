package com.dadingcoding.web.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity @Getter
public class QuestionAnswer extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_answer_id")
    private Long id;

    private String title;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private QuestionAnswer question;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "question", orphanRemoval = true)
    private List<QuestionAnswer> answers;

    @Enumerated(EnumType.STRING)
    private QnaType qnaType;
}
