package com.stackroute.exception;

//Custom Exception when Domain is not found

public class DomainNotFoundException extends Exception{
    private String message;

    public DomainNotFoundException()
    {

    }

    public DomainNotFoundException( String message )
    {
        super(message);
        this.message = message;
    }
}
