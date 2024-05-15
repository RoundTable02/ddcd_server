package com.dadingcoding.web.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "visibility")
    private String visibility;

    @Builder
    public Notice(String title, String content, String visibility) {
        this.title = title;
        this.content = content;
        this.visibility = visibility;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @CreatedDate
    @Column(name = "datePosted")
    private LocalDateTime datePosted;

    @ManyToOne
    private Member member;
}
