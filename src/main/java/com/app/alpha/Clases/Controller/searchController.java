package com.app.alpha.Clases.Controller;

import com.app.alpha.Clases.DLPService;

import com.app.alpha.Clases.DTO.InfoDTO;
import com.app.alpha.Clases.JsonService;
import com.sapher.youtubedl.YoutubeDLException;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class searchController {
    private final DLPService dlpService;
    private final JsonService jsonService;

    public searchController(DLPService dlpService, JsonService jsonService) {
        this.dlpService = dlpService;
        this.jsonService = jsonService;
    }


    @GetMapping("/info")
    public InfoDTO searchResponse(@RequestParam String url) throws YoutubeDLException { //volver a cambiar la respuesta a CompletableFuture
        //return dlpService.infoUrl(url);
        return jsonService.leerDatosJson(); //testeo
    }


}
