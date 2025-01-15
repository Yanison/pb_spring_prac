package com.pb.starter.subject;

import com.pb.starter.model.SubjectEntity;
import com.pb.starter.model.SubjectSearchParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface SubjectService {
    Optional<SubjectEntity> findById(String id);
    List<SubjectEntity> findAll();
    int insert(SubjectEntity subject);
    int delete(String id);
    int update(SubjectEntity subject);
    Page<SubjectEntity> pagedList(SubjectSearchParam searchParam, Pageable pageable);
}
