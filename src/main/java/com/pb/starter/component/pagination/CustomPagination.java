package com.pb.starter.component.pagination;

import lombok.Data;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

@Data
public class CustomPagination<L,S,M> {

    private final List<L> list;
    private final S service;
    private final M mapper;

    public CustomPagination(List<L> list, S service, M mapper) {
        this.list = list;

        // S의 어노테이션이 @Service인지 확인


        // M의 어노테이션이 @Mapper인지 확인
        if (!hasAnnotation(mapper.getClass(), Mapper.class)) {
            throw new IllegalArgumentException("The provided mapper does not have the @Mapper annotation");
        }
        this.service = serviceClassValidation(service);
        this.mapper = mapperClassValidation(mapper);
    }

    private boolean hasAnnotation(Class<?> clazz, Class<? extends Annotation> annotationClass) {
        return clazz.isAnnotationPresent(annotationClass);
    }

    public S serviceClassValidation(S service) {
        if (!hasAnnotation(service.getClass(), Service.class)) {
            throw new IllegalArgumentException("The provided service does not have the @Service annotation");
        }

        // S에 pagedList 메소드가 있는지 확인
        try {
            service.getClass().getMethod("pagedList", Pageable.class);
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("The provided service does not have the pagedList method");
        }

        return service;
    }

    public M mapperClassValidation(M mapper) {
        if (!hasAnnotation(mapper.getClass(), Mapper.class)) {
            throw new IllegalArgumentException("The provided mapper does not have the @Mapper annotation");
        }

        // M에 pagedList 메소드가 있는지 확인
        try {
            Method method = mapper.getClass().getMethod("pagedList", List.class);

            // 메서드 매개변수가 List<T>인지 확인
            if (!isMethodParameterGenericListOf(method, list.get(0).getClass())) {
                throw new IllegalArgumentException("The pagedList method does not accept List<T> where T matches the list's type");
            }

        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("The provided mapper does not have the pagedList method with List<T> parameter");
        }

        return mapper;
    }

    private boolean isMethodParameterGenericListOf(Method method, Class<?> targetType) {
        // 메서드의 매개변수 타입 확인
        Class<?>[] parameterTypes = method.getParameterTypes();
        if (parameterTypes.length != 1 || !List.class.isAssignableFrom(parameterTypes[0])) {
            return false; // 매개변수가 List 타입이 아닌 경우
        }

        // 제네릭 타입 확인
        Type[] genericParameterTypes = method.getGenericParameterTypes();
        if (genericParameterTypes.length == 1 && genericParameterTypes[0] instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericParameterTypes[0];
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();

            // List<T>의 T가 targetType과 일치하는지 확인
            return actualTypeArguments.length == 1 && actualTypeArguments[0].equals(targetType);
        }

        return false; // 제네릭 타입이 일치하지 않는 경우
    }
}
