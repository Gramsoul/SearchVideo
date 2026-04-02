package com.app.alpha.Clases.Controller;

import com.app.alpha.Clases.DLPService;

import com.app.alpha.Clases.DTO.InfoDTO;
import com.app.alpha.Clases.JsonService;
import com.sapher.youtubedl.YoutubeDLException;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<?> searchResponse(@RequestParam String url) throws YoutubeDLException, IOException, ExecutionException, InterruptedException { //volver a cambiar la respuesta a CompletableFuture
        if (url == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("errorDTO");
        }
        //InfoDTO response = dlpService.infoUrl(url).get();//get porque es un completable future
        InfoDTO response = jsonService.leerDatosJson();
        return ResponseEntity.ok().body(response);
    }


}
