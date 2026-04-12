package com.app.alpha.Exceptions;

import com.app.alpha.Exceptions.ErrorHandlers.ErrorDTO;
import com.sapher.youtubedl.YoutubeDLException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UrlNotFoundException.class)
    public ResponseEntity<ErrorDTO> urlNotFound(UrlNotFoundException ex, WebRequest request) {
        ErrorDTO e = new ErrorDTO(ex.getMessage(), request.getDescription(false));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
    }

    @ExceptionHandler(YoutubeDLException.class)
    public ResponseEntity<ErrorDTO> youtubeDL(YoutubeDLException ex, WebRequest request) {
        ErrorDTO e = new ErrorDTO("Fallo en yt-dlp", request.getDescription(false));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
    }

    @ExceptionHandler(StreamFailException.class)
    public ResponseEntity<ErrorDTO> StreamFail(StreamFailException ex, WebRequest request){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ErrorDTO("Fallo en la creacion del stream", request.getDescription(false))
        );
    }

}
