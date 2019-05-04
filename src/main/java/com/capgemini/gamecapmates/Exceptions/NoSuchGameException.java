package com.capgemini.gamecapmates.Exceptions;

public class NoSuchGameException extends Exception {

    private static final long serialVersionUID = 1L;

    public NoSuchGameException(){
        super("No such game, please add new game");
    }
}
