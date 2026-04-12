package com.app.alpha.Clases.Controller;

import com.app.alpha.Clases.DLPService;

import com.app.alpha.Clases.DTO.InfoDTO;
import com.app.alpha.Clases.JsonService;
import com.sapher.youtubedl.YoutubeDLException;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.IOException;
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
    public ResponseEntity<StreamingResponseBody> download(
            @NotBlank @RequestParam String url,
            @NotBlank @RequestParam String format_id) {

        return dlpService.downloadStream(url, format_id);
    }


}
