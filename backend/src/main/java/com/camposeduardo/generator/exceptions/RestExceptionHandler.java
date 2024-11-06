package com.camposeduardo.generator.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(InvalidPlaylistException.class)
    public ResponseEntity<RestErrorMessage> invalidPlaylistHandler(InvalidPlaylistException exception) {
        RestErrorMessage message = new RestErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
    @ExceptionHandler(InvalidSpofityTokenException.class)
    public ResponseEntity<RestErrorMessage> invalidTokenHandler(InvalidSpofityTokenException exception) {
        RestErrorMessage message = new RestErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @ExceptionHandler(InvalidSpotifyResponseBodyException.class)
    public ResponseEntity<RestErrorMessage> invalidSpotifyResponseHandler(InvalidSpotifyResponseBodyException exception) {
        RestErrorMessage message = new RestErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
    }

    @ExceptionHandler(SpotifyApiErrorException.class)
    public ResponseEntity<RestErrorMessage> spotifyApiErrorHandler(SpotifyApiErrorException exception) {
        RestErrorMessage message = new RestErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
    }

    @ExceptionHandler(UserIdInvalidException.class)
    public ResponseEntity<RestErrorMessage> InvalidUserIdHandler(UserIdInvalidException exception) {
        RestErrorMessage message = new RestErrorMessage(HttpStatus.BAD_REQUEST, exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }
}
