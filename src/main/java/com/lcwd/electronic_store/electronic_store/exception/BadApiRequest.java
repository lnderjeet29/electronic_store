package com.lcwd.electronic_store.electronic_store.exception;

public class BadApiRequest extends RuntimeException{
    public BadApiRequest(String message){
        super(message);
    }
    public BadApiRequest(){
        super("Bad request api!!");
    }
}
