package com.lcwd.electronic_store.electronic_store.exception;

import com.lcwd.electronic_store.electronic_store.dto.ApiRepositoryMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiRepositoryMessage> resourceNotFound(ResourceNotFoundException resource) {
        ApiRepositoryMessage message=ApiRepositoryMessage.builder().message(resource.getMessage()).status(false).httpStatus(HttpStatus.NOT_FOUND).build();
        return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(BadApiRequest.class)
    public ResponseEntity<ApiRepositoryMessage> BadApiRequestException(BadApiRequest resource) {
        ApiRepositoryMessage message=ApiRepositoryMessage.builder().message(resource.getMessage()).status(false).httpStatus(HttpStatus.BAD_REQUEST).build();
        return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ApiRepositoryMessage> DataNotFoundException(DataNotFoundException resource){
        ApiRepositoryMessage message=ApiRepositoryMessage.builder().message(resource.getMessage()).status(false).httpStatus(HttpStatus.NOT_FOUND).build();
        return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
    }
}
