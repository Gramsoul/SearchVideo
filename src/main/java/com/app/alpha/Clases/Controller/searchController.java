package com.app.alpha.Clases.Controller;

import com.app.alpha.Clases.DLP;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Controller
public class searchController {
    private final DLP dlp;

    public searchController(DLP dlp) {
        this.dlp = dlp;
    }

    @PostMapping("/info")
    public CompletableFuture<Map<String, Object>> getInfo(@RequestParam String videoUrl) {
        return dlp.info(videoUrl)
                .thenApply(ResponseEntity::ok);
//                .exceptionally(ResponseEntity::internalServerError);
    }


}
