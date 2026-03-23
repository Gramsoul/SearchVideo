package com.app.alpha.Clases;

import com.app.alpha.Interfaces.Downloadeable;
import com.sapher.youtubedl.YoutubeDL;
import com.sapher.youtubedl.YoutubeDLException;
import com.sapher.youtubedl.YoutubeDLRequest;
import com.sapher.youtubedl.YoutubeDLResponse;
import com.sapher.youtubedl.mapper.VideoFormat;
import com.sapher.youtubedl.mapper.VideoInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Component
public class DLP implements Downloadeable {
    public String directory = ""; //Aca va el lugar de descarga

    @Autowired
    private ObjectMapper objectMapper;

    public DLP(){}

    @Async("downloadExecutor")
    public String info(String videoUrl){
        try {
            VideoInfo infoUrl = YoutubeDL.getVideoInfo(videoUrl);
            Map<String, Object> data = new HashMap<>();

            data.put("url", infoUrl.webpageUrlBasename);
            data.put("title", infoUrl.fulltitle);
            data.put("thumbnail", infoUrl.thumbnail);
            data.put("uploader", infoUrl.uploader);
            data.put("description", infoUrl.description);

            return objectMapper.writeValueAsString(data);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Async("downloadExecutor") // -> pool especifico para descargas
    public void download(String videoUrl){
        YoutubeDL.setExecutablePath(""); //Path de yt-dlp.exe

        try{
            YoutubeDLRequest request = request(videoUrl, directory);
            response(request);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private YoutubeDLRequest request(String videoUrl, String directory){

        YoutubeDLRequest request = new YoutubeDLRequest(videoUrl, directory);
        request.setOption("ffmpeg-location", "D:\\LIBS-PATH\\ffmpeg.exe"); //ubicacion de ffmpeg.exe
        request.setOption("referer", videoUrl);

        request.setOption("ignore-errors");
        request.setOption("no-overwrites");
        request.setOption("format", "bestvideo+bestaudio/best");
        request.setOption("merge-output-format", "mp4");
        request.setOption("output", "%(title)s.%(ext)s");
        request.setOption("retries", 10);

        return request;
    }

    private YoutubeDLResponse response(YoutubeDLRequest request){
        YoutubeDLResponse response;
        try {
            response = YoutubeDL.execute(request);
        } catch (YoutubeDLException e) {
            throw new RuntimeException(e);
        }

        return response;
    }


}
