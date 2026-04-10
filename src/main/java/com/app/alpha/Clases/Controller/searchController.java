package com.app.alpha.Clases.Controller;

import com.app.alpha.Clases.DLPService;

import com.app.alpha.Clases.DTO.InfoDTO;
import com.app.alpha.Clases.JsonService;
import com.app.alpha.Exceptions.ErrorHandlers.ErrorDTO;
import com.sapher.youtubedl.YoutubeDLException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jdk.jshell.Snippet;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class searchController {
    private final DLPService dlpService;
    private final JsonService jsonService;

    public searchController(DLPService dlpService, JsonService jsonService) {
        this.dlpService = dlpService;
        this.jsonService = jsonService;
    }

    @GetMapping("/info")
    public ResponseEntity<InfoDTO> searchResponse(@NotBlank @RequestParam String url)
            throws YoutubeDLException, IOException {

        //return jsonService.leerDatosJson();
        return ResponseEntity.ok().body(dlpService.infoUrl(url));
    }

    @GetMapping("/download")
    public ResponseEntity<InfoDTO> download(
            @RequestParam String url,
            @RequestParam String format_id) {

        return ResponseEntity.ok().build();
    }


}
