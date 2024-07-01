package com.dadingcoding.web.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@MappedSuperclass
@Data
public abstract class PostEntity extends BaseEntity {
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;
}
