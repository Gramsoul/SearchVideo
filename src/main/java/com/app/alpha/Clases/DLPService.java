package com.app.alpha.Clases;

import com.app.alpha.Clases.DTO.InfoDTO;
import com.sapher.youtubedl.YoutubeDL;
import com.sapher.youtubedl.YoutubeDLException;
import com.sapher.youtubedl.YoutubeDLRequest;
import com.sapher.youtubedl.YoutubeDLResponse;
import com.sapher.youtubedl.mapper.VideoFormat;
import com.sapher.youtubedl.mapper.VideoInfo;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class DLPService{
    private List<VideoFormat> filterFormats(List<VideoFormat> formats) {
        Map<Integer, VideoFormat> filteredFormats = formats.stream()
                .filter(f -> f.ext != null && f.ext.equalsIgnoreCase("mp4"))
                .filter(f -> List.of(144, 240, 360, 480, 720, 1080, 1440, 2160).contains(f.height))
                .collect(Collectors.toMap(
                        f -> f.height,
                        f -> f,
                        (a, b) -> a.filesize > b.filesize ? a : b
                ));
        return new ArrayList<>(filteredFormats.values());
    }

    public InfoDTO infoUrl(String videoUrl) throws YoutubeDLException {
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

            return data;
        } catch (YoutubeDLException e) {
            throw new YoutubeDLException(e);
        }
    }

    @Async("downloadExecutor") // -> pool especifico para descargas
    public void download(String videoUrl, String format, String directory) throws YoutubeDLException {

        YoutubeDL.setExecutablePath("src/main/resources/bin/yt-dlp.exe"); //Path de yt-dlp.exe
        try {
            YoutubeDLRequest request = request(videoUrl, format);
            YoutubeDL.execute(request);
        } catch (YoutubeDLException | IOException e) {
            throw new YoutubeDLException(e);
        }
    }

    public void downloadStream(String url, String format) throws IOException {
        File tempFile = File.createTempFile("video_", ".mp4");

    }

    private YoutubeDLRequest request(String videoUrl, String format) throws IOException {

        YoutubeDLRequest request = new YoutubeDLRequest(videoUrl);

        String ffmpegPath = new File("src/main/resources/bin/ffmpeg.exe").getAbsolutePath();

        request.setOption("ffmpeg-location", ffmpegPath); //ubicacion de ffmpeg.exe
        request.setOption("referer", videoUrl);
        request.setOption("ignore-errors");
        request.setOption("js-runtimes", "node");
        request.setOption("no-overwrites", "");

        //actualmente de selecciona por defecto el mejor audio
        String finalFormat = format + "+bestaudio";
        request.setOption("format", finalFormat);

        request.setOption("merge-output-format", "mp4");
        request.setOption("retries", 10);

        return request;
    }


}
