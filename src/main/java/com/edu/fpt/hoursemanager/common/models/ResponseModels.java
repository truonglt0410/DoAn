package com.edu.fpt.hoursemanager.common.models;

import com.edu.fpt.hoursemanager.common.enums.ResponseMessage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseModels {
    public final long timestamp = System.currentTimeMillis();
    public String message;
    public String code;
    private Object data;

    public static <T>ResponseEntity<ResponseModels> success(){
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseModels.builder()
                        .message(ResponseMessage.SUCCESSFUL.getMessage())
                        .code(ResponseMessage.SUCCESSFUL.getCode())
                        .build());
    }

    public static <T>ResponseEntity<ResponseModels> success(T data){
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseModels.builder()
                        .data(data)
                        .message(ResponseMessage.SUCCESSFUL.getMessage())
                        .code(ResponseMessage.SUCCESSFUL.getCode())
                        .build());
    }

    public static <T>ResponseEntity<ResponseModels> success(String message){
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseModels.builder()
                        .message(message)
                        .code(ResponseMessage.SUCCESSFUL.getCode())
                        .build());
    }
    public static <T>ResponseEntity<ResponseModels> success(T data, String message){
        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseModels.builder()
                        .data(data)
                        .message(message)
                        .code(ResponseMessage.SUCCESSFUL.getCode())
                        .build());
    }

    public static <T>ResponseEntity<ResponseModels> created(){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponseModels.builder()
                        .message(ResponseMessage.CREATED.getMessage())
                        .code(ResponseMessage.CREATED.getCode())
                        .build());
    }

    public static <T>ResponseEntity<ResponseModels> created(T data){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponseModels.builder()
                        .data(data)
                        .message(ResponseMessage.CREATED.getMessage())
                        .code(ResponseMessage.CREATED.getCode())
                        .build());
    }

    public static <T>ResponseEntity<ResponseModels> created(String message){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponseModels.builder()
                        .message(message)
                        .code(ResponseMessage.CREATED.getCode())
                        .build());
    }

    public static <T>ResponseEntity<ResponseModels> created(T data, String message){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ResponseModels.builder()
                        .data(data)
                        .message(message)
                        .code(ResponseMessage.CREATED.getCode())
                        .build());
    }

    public static <T>ResponseEntity<ResponseModels> error(){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ResponseModels.builder()
                        .message(ResponseMessage.INTERNAL_SERVER_ERROR.getMessage())
                        .code(ResponseMessage.INTERNAL_SERVER_ERROR.getCode())
                        .build());
    }

    public static <T>ResponseEntity<ResponseModels> error(T data){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ResponseModels.builder()
                        .data(data)
                        .message(ResponseMessage.INTERNAL_SERVER_ERROR.getMessage())
                        .code(ResponseMessage.INTERNAL_SERVER_ERROR.getCode())
                        .build());
    }

    public static <T>ResponseEntity<ResponseModels> error(String message){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ResponseModels.builder()
                        .message(message)
                        .code(ResponseMessage.INTERNAL_SERVER_ERROR.getCode())
                        .build());
    }

    public static <T>ResponseEntity<ResponseModels> error(T data, String message){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                ResponseModels.builder()
                        .data(data)
                        .message(message)
                        .code(ResponseMessage.INTERNAL_SERVER_ERROR.getCode())
                        .build());
    }

    public static <T>ResponseEntity<ResponseModels> unauthorized(T data){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                ResponseModels.builder()
                        .data(data)
                        .message(ResponseMessage.UNAUTHORIZED.getMessage())
                        .code(ResponseMessage.UNAUTHORIZED.getCode())
                        .build());
    }

    public static <T>ResponseEntity<ResponseModels> unauthorized(){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                ResponseModels.builder()
                        .message(ResponseMessage.UNAUTHORIZED.getMessage())
                        .code(ResponseMessage.UNAUTHORIZED.getCode())
                        .build());
    }

    public static <T>ResponseEntity<ResponseModels> notFound(T data){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ResponseModels.builder()
                        .data(data)
                        .message(ResponseMessage.NOT_FOUND.getMessage())
                        .code(ResponseMessage.NOT_FOUND.getCode())
                        .build());
    }

    public static <T>ResponseEntity<ResponseModels> timeOut(){
        return ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).body(
                ResponseModels.builder()
                        .message(ResponseMessage.REQUEST_TIMEOUT.getMessage())
                        .code(ResponseMessage.REQUEST_TIMEOUT.getCode())
                        .build());
    }

    public static <T>ResponseEntity<ResponseModels> conflict(){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                ResponseModels.builder()
                        .message(ResponseMessage.CONFLICT.getMessage())
                        .code(ResponseMessage.CONFLICT.getCode())
                        .build());
    }

}
