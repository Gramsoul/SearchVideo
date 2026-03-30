package com.app.alpha.Clases.Controller;

import com.app.alpha.Clases.DLP;

import com.app.alpha.Clases.DTO.InfoDTO;
import com.sapher.youtubedl.YoutubeDLException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.CompletableFuture;

@Controller
public class searchController {
    private final DLP dlpService;

    public searchController(DLP dlpService) {
        this.dlpService = dlpService;
    }


    @GetMapping("/videoinfo")
    public CompletableFuture<InfoDTO> searchResponse(@RequestParam String videoUrl) throws YoutubeDLException {
        return dlpService.infoUrl(videoUrl);
    }


}
