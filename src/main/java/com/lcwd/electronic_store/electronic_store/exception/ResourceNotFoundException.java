package com.lcwd.electronic_store.electronic_store.exception;

import lombok.Builder;

@Builder
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(){
        super("resource not founded!!");
    }
    public ResourceNotFoundException(String message){
        super(message);
    }
}
