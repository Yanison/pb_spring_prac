package com.pb.starter.subject;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import static com.pb.starter.model.constant.Constant.*;

@Component
@RequiredArgsConstructor
public class SubjectServiceContext {

    private final ApplicationContext applicationContext;
    public SubjectService getService(String condition){
        return condition.equals(BASIC_STRATEGY) ?
                applicationContext.getBean(SubjectServiceImpl1.class) :
                applicationContext.getBean(SubjectServiceImpl2.class) ;
    }
}
