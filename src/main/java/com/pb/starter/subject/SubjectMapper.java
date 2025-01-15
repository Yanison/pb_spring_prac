package com.pb.starter.subject;

import com.pb.starter.model.SubjectEntity;
import com.pb.starter.model.SubjectSearchParam;
import org.apache.ibatis.annotations.*;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Mapper
public interface SubjectMapper {

    @Select("SELECT id, title, content, user_id AS userId, uuid, reg_dt AS regDt, mod_dt AS modDt FROM subject WHERE id = #{id}")
    Optional<SubjectEntity> findById(Long id);

    @Select("SELECT id, title, content, user_id AS userId, uuid, reg_dt AS regDt, mod_dt AS modDt FROM subject WHERE uuid = #{uuid}")
    Optional<SubjectEntity> findByUUID(String uuid);


    @Select("SELECT id, title, content, user_id AS userId, uuid, reg_dt AS regDt, mod_dt AS modDt FROM subject")
    List<SubjectEntity> findAll();

    @Select("""
        SELECT id, title, content, user_id AS userId, uuid, reg_dt AS regDt, mod_dt AS modDt
                FROM subject
                WHERE 1=1 
                and title LIKE CONCAT('%', #{searchParam.title}, '%')
                and content LIKE CONCAT('%', #{searchParam.content}, '%')
                ORDER BY id
                LIMIT #{pageable.pageSize} OFFSET #{pageable.offset}
    """)
    List<SubjectEntity> pagedList(@Param("searchParam") SubjectSearchParam searchParam, @Param("pageable") Pageable pageable);

    @Select("SELECT COUNT(*) FROM subject")
    int countAll();

    @Insert("""
        INSERT INTO subject
            (
            title,
            content,
            user_id,
            uuid
            )
        VALUES (
        #{title},
        #{content},
        #{userId},
        #{uuid}
        )
    """)
    int insert(SubjectEntity subject);

    @Delete("DELETE FROM subject WHERE id = #{id}")
    int deleteById(Long id);

    @Delete("DELETE FROM subject WHERE uuid = #{uuid}")
    int deleteByUuid(String id);

    @Update("""
        UPDATE subject
        SET
            title = #{title},
            content = #{content}
        WHERE id = #{id}
    """)
    int update(SubjectEntity subject);
}
