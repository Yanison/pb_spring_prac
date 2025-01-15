package com.pb.starter.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springdoc.core.converters.models.Pageable;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class SubjectEntity {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime regDt;
    private LocalDateTime modDt;
    private String uuid;
    private String category;
    private boolean favorite;
    private Long userId;

    @Builder
    public SubjectEntity(Long id, String title, String content, Long userId,String uuid, String category, boolean favorite) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.uuid = uuid;
        this.category = category;
        this.favorite = favorite;
    }

}
