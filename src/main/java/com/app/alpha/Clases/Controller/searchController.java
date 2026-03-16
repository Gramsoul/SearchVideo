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


    @GetMapping("/")
    public String index(Model model){
       model.addAttribute("contenido", "form :: searchSection");
       return "layout";
    }

    @PostMapping("/response")
    public String searchResponse(@RequestParam String urlVideo, Model model){
        System.out.println("url buscada: "+urlVideo);

        VideoInfo data = Info.obtainData(urlVideo);
        model.addAttribute("urlDownload", data.webpageUrl);
        model.addAttribute("thumbnail", data.thumbnail);
        model.addAttribute("fulltitle", data.fulltitle);
        model.addAttribute("uploader", data.uploader);
        model.addAttribute("description", data.description);

        model.addAttribute("contenido", "response :: response");

        return "layout";
    }
    @PostMapping("/download")
    public String executeDownload(@RequestParam("urlDownload") String urlDownload ,Model model){
        DLP dlp = new DLP();
        dlp.download(urlDownload);

        model.addAttribute("contenido", "download :: download");
        return "layout";
    }


}
