package com.pb.starter.subject;

import com.pb.starter.model.SubjectEntity;
import com.pb.starter.model.SubjectSearchParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@Mapper
public interface SubjectMapper {

    Optional<SubjectEntity> findById(Long id);

    Optional<SubjectEntity> findByUUID(String uuid);

    List<SubjectEntity> findAll();

    List<SubjectEntity> pagedList(@Param("searchParam") SubjectSearchParam searchParam, @Param("pageable") Pageable pageable);

    int countAll();

    int insert(SubjectEntity subject);

    int deleteById(Long id);

    int deleteByUuid(String uuid);

    int update(SubjectEntity subject);
}

