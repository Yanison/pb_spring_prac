package com.pb.starter.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SubjectEntity {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime regDt;
    private LocalDateTime modDt;
    private String uuid;
    private Long userId;

    @Builder
    public SubjectEntity(Long id, String title, String content, Long userId,String uuid) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.uuid = uuid;
    }

}
