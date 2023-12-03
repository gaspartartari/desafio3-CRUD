package com.learning.desafios.services.exceptions;

public class ResourceNotfoundException extends RuntimeException {
    
    public ResourceNotfoundException(String msg){
        super(msg);
    }
}
