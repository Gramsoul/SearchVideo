package com.app.alpha.Clases;

import com.app.alpha.Clases.DTO.InfoDTO;
import com.app.alpha.Interfaces.Downloadeable;
import com.sapher.youtubedl.YoutubeDL;
import com.sapher.youtubedl.YoutubeDLException;
import com.sapher.youtubedl.YoutubeDLRequest;
import com.sapher.youtubedl.YoutubeDLResponse;
import com.sapher.youtubedl.mapper.VideoFormat;
import com.sapher.youtubedl.mapper.VideoInfo;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class DLPService implements Downloadeable {
    public String directory = ""; //Aca va el lugar de descarga

    private List<VideoFormat> filterFormats(List<VideoFormat> formats) {
        Map<Integer, VideoFormat> filteredFormats = formats.stream()
                .filter(f -> f.ext != null && f.ext.equalsIgnoreCase("mp4"))
                .filter(f -> List.of(144, 240, 360, 480, 720, 1080, 1440, 2160).contains(f.height))
                .collect(Collectors.toMap(
                        f-> f.height,
                        f -> f,
                        (a,b) -> a.filesize > b.filesize ? a : b
                ));
        return new ArrayList<>(filteredFormats.values());
    }

    @Async
    public CompletableFuture<InfoDTO> infoUrl(String videoUrl) throws YoutubeDLException {
        try {
            YoutubeDL.setExecutablePath("src/main/resources/bin/yt-dlp.exe"); //Path de yt-dlp.exe

            VideoInfo infoUrl = YoutubeDL.getVideoInfo(videoUrl);
            InfoDTO data = new InfoDTO();

            data.setUrl(infoUrl.webpageUrl);
            data.setTitle(infoUrl.title);
            data.setThumbnail(infoUrl.thumbnail);
            data.setUploader(infoUrl.uploader);
            data.setDescription(infoUrl.description);
            data.setFormats(filterFormats(infoUrl.formats));

            return CompletableFuture.completedFuture(data);
        } catch (YoutubeDLException e) {
            throw new YoutubeDLException(e);
        }
    }

    @Async("downloadExecutor") // -> pool especifico para descargas
    public void download(String videoUrl) {
        YoutubeDL.setExecutablePath(""); //Path de yt-dlp.exe

        try {
            YoutubeDLRequest request = request(videoUrl, directory);
            response(request);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private YoutubeDLRequest request(String videoUrl, String directory) {

        YoutubeDLRequest request = new YoutubeDLRequest(videoUrl, directory);
        request.setOption("ffmpeg-location", "src/main/resources/bin/ffmpeg.exe"); //ubicacion de ffmpeg.exe
        request.setOption("referer", videoUrl);

        request.setOption("ignore-errors");
        request.setOption("no-overwrites");
        request.setOption("format", "bestvideo+bestaudio/best");
        request.setOption("merge-output-format", "mp4");
        request.setOption("output", "%(title)s.%(ext)s");
        request.setOption("retries", 10);

        return request;
    }

    private YoutubeDLResponse response(YoutubeDLRequest request) {
        YoutubeDLResponse response;
        try {
            response = YoutubeDL.execute(request);
        } catch (YoutubeDLException e) {
            throw new RuntimeException(e);
        }

        return response;
    }


}
