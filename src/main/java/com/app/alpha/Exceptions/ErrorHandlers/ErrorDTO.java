package com.app.alpha.Exceptions.ErrorHandlers;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;


import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

@Data
public class ErrorDTO {
    private final Timestamp time;
    @NotNull
    private String messaje;
    private String path;

    public ErrorDTO(String messaje, String path){
        time = Timestamp.from(Instant.now());
        this.messaje = messaje;
        this.path = path;
    }
}
