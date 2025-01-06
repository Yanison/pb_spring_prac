package com.pb.starter.model.api;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class CustomApiResponse<T> {
    private final T data;
    private final HttpStatus status;
    private final String message;

    @Builder
    public CustomApiResponse(T data, HttpStatus status,String message){
        this.data = data;
        this.status = status;
        this.message = message;
    }

    public CustomApiResponse toResponse(T data, HttpStatus status,String message){
        return CustomApiResponse.builder()
                .data(data)
                .status(status)
                .message(message)
                .build();
    }
}
