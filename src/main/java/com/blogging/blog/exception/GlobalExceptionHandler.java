package com.blogging.blog.exception;

import com.blogging.blog.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value={ResourceNotFoundException.class})
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
        String message=ex.getMessage();
        ApiResponse response=new ApiResponse(message,false);
        return  new ResponseEntity<ApiResponse>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<Map<String,String>> handleMethodArgumentsNotValidException(MethodArgumentNotValidException methodArgumentNotValidException){
        Map<String,String> resp=new HashMap<>();
        methodArgumentNotValidException.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName=((FieldError)error).getField();
            String defaultMessage=error.getDefaultMessage();
            resp.put(fieldName,defaultMessage);
        });
        return new ResponseEntity<>(resp,HttpStatus.BAD_REQUEST);
    }
}
