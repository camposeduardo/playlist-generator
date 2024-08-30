package com.camposeduardo.generator.exceptions;

public class InvalidPlaylistException extends RuntimeException{
    public InvalidPlaylistException () {
        super("Invalid playlist information.");
    }
}
