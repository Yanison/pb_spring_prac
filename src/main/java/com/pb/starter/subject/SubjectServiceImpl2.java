package com.pb.starter.subject;

import com.pb.starter.model.SubjectEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubjectServiceImpl2 implements SubjectService{

    private final SubjectMapper subjectMapper;

    @Override
    public Optional<SubjectEntity> findById(String id) {

        return subjectMapper.findById(Long.valueOf(id));
    }

    @Override
    public List<SubjectEntity> findAll() {
        return subjectMapper.findAll();
    }

    @Override
    public int insert(SubjectEntity subject) {
        return subjectMapper.insert(subject);
    }

    @Override
    public int delete(Long id) {
        return subjectMapper.delete(id);
    }

    @Override
    public int update(SubjectEntity subject) {
        return subjectMapper.update(subject);
    }
}
