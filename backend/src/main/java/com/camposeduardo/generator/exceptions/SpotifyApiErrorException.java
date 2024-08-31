package com.camposeduardo.generator.exceptions;

public class SpotifyApiErrorException extends RuntimeException{
    public SpotifyApiErrorException() {
        super("An error occurred while communicating with Spotify API.");
    }
}
