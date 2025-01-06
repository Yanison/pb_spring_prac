package com.pb.starter.subject;

import com.pb.starter.model.SubjectEntity;

import java.util.List;
import java.util.Optional;

public interface SubjectService {
    Optional<SubjectEntity> findById(String id);
    List<SubjectEntity> findAll();
    int insert(SubjectEntity subject);
    int delete(Long id);
    int update(SubjectEntity subject);
}
