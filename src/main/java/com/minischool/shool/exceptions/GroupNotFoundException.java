package com.minischool.shool.exceptions;

public class GroupNotFoundException  extends RuntimeException{

    private static final long serialVersionUID = 1;

    public GroupNotFoundException(String message){
        super(message);
    }
}
