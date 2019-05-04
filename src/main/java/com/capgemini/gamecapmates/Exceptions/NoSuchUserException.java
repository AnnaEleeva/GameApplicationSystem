package com.capgemini.gamecapmates.Exceptions;

public class NoSuchUserException extends Exception {

    private static final long serialVersionUID = 1L;

    public NoSuchUserException(){
        super("No such user , choose another id");
    }
}
