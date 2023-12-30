package com.lcwd.electronic_store.electronic_store.exception;

public class DataNotFoundException extends RuntimeException{
    public DataNotFoundException(){
        super("data not found...");
    }
    public DataNotFoundException(String message){
        super(message);
    }
}
