package com.minischool.shool.exceptions;

public class StudentNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 2;

    public StudentNotFoundException(String message){
        super(message);
    }

}
