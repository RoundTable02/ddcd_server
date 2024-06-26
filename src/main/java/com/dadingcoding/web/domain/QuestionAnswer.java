package com.dadingcoding.web.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class QuestionAnswer extends BaseEntity{
    @Id
    @GeneratedValue
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

    @OneToMany(mappedBy = "question", orphanRemoval = true)
    private List<QuestionAnswer> answers;

    @Enumerated(EnumType.STRING)
    private QnaType qnaType;
}
