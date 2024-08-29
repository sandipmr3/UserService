package com.sandip.user.service.UserService.exception;

public class ResourceNotFoundException extends RuntimeException{

    //Add extra properties that you want manage
    public ResourceNotFoundException(){

        super("Resource Not Found Server .. ! ");
    }
    public ResourceNotFoundException(String message){

        super(message);
    }
}
