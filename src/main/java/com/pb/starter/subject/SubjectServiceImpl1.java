package com.pb.starter.subject;

import com.pb.starter.model.SubjectEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.pb.starter.component.util.CommonUtil.uuidGenerator;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubjectServiceImpl1 implements SubjectService{

    private final SubjectMapper subjectMapper;
    @Override
    public Optional<SubjectEntity> findById(String id) {
        return subjectMapper.findByUUID(id);
    }

    @Override
    public List<SubjectEntity> findAll() {
        return subjectMapper.findAll();
    }

    @Override
    public int insert(SubjectEntity subject) {
        subject.setUuid(
                uuidGenerator(subject.getTitle(),subject.getContent())
        );
        return subjectMapper.insert(subject);
    }

    @Override
    public int delete(String id) {
        return subjectMapper.deleteByUuid(id);
    }


    @Override
    public int update(SubjectEntity subject) {
        return subjectMapper.update(subject);
    }
}
