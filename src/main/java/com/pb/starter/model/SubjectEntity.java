package com.pb.starter.model;

import lombok.Builder;
import lombok.Data;

@Data
public class SubjectEntity {
    private Long id;
    private String title;
    private String content;
    private Long userId;
    private String uuid;
    private String regDt;
    private String modDt;

    @Builder
    public SubjectEntity(Long id, String title, String content, Long userId,String uuid) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.uuid = uuid;
    }

}
