package com.app.alpha.Clases.Controller;

import com.app.alpha.Clases.DLP;
import com.app.alpha.Clases.Info;
import com.sapher.youtubedl.mapper.VideoInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class searchController {
    private final DLP dlp;

    public searchController(DLP dlp){
        this.dlp = dlp;
    }

    @PostMapping("/response")
    public String searchResponse(@RequestParam String videoUrl){
        return dlp.info(videoUrl);
    }

//    @PostMapping("/download")
//    public String executeDownload(@RequestParam("urlDownload") String urlDownload ,Model model){
//        DLP dlp = new DLP();
//        dlp.download(urlDownload);
//
//        model.addAttribute("contenido", "download :: download");
//        return "layout";
//    }


}
