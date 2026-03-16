package com.app.alpha.Clases;

import com.sapher.youtubedl.YoutubeDL;
import com.sapher.youtubedl.YoutubeDLException;
import com.sapher.youtubedl.mapper.VideoInfo;
import org.springframework.web.bind.annotation.GetMapping;


public class Info {

    static {
        YoutubeDL.setExecutablePath("D:\\LIBS-PATH\\yt-dlp.exe");
    }

    public static VideoInfo obtainData(String urlVideo){
        try {
            return YoutubeDL.getVideoInfo(urlVideo);
        } catch (YoutubeDLException e) {
            throw new RuntimeException(e);
        }
    }
    public void resumenVideo(String urlVideo){
        VideoInfo data = obtainData(urlVideo);

        System.out.println("Titulo: "+data.fulltitle);
        System.out.println("------------------------------------");
        System.out.println("Descripcion: "+data.description);
        System.out.println("------------------------------------");
        System.out.println("Uploader: "+data.uploader);
        System.out.println("Duracion: "+data.duration+"s"); //en segundos
        System.out.println("url-thubmail: "+data.thumbnail);
        System.out.println("url-video: "+data.webpageUrl);
    }

}
