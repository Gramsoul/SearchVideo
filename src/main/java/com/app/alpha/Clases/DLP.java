package com.app.alpha.Clases;

import com.sapher.youtubedl.YoutubeDL;
import com.sapher.youtubedl.YoutubeDLException;
import com.sapher.youtubedl.YoutubeDLRequest;
import com.sapher.youtubedl.YoutubeDLResponse;
import com.sapher.youtubedl.mapper.VideoFormat;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.Scanner;

public class DLP {

    @Async("downloadExecutor") // -> pool especifico para descargas (no esta creado el @Config, es probable que tire error)
    public void download(String videoUrl){
        String directory = "D:\\Download"; //Aca va el lugar de descarga
        YoutubeDL.setExecutablePath("D:\\LIBS-PATH\\yt-dlp.exe"); //Path de yt-dlp.exe

        try{
            YoutubeDLRequest request = request(videoUrl, directory);
            response(request);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private VideoFormat selectionFormat(List<VideoFormat> formats, Scanner sc){
        VideoFormat formatSelection;

        int opt = 0 ;
        boolean f = false;
        while (!f){
            System.out.println("Seleccione resolucion: ");
            for(int i=0; i <formats.toArray().length; i++){
                VideoFormat format = formats.get(i);
                if (format.acodec != null && format.height > 480){
                    System.out.print(i+" - "+ formats.get(i).width+ "x"+formats.get(i).height+ " ");
                    int kb = formats.get(i).filesize/ 1024; //yt-dlp devuelve el peso en bites.
                    int mb = kb/ 1024;
                    System.out.print(" "+mb+"mb");
                    System.out.println();
                }
            }
            System.out.println();
            //opt = sc.nextInt();
            f = true;
        }
        formatSelection = formats.get(opt); // Formato seleccionado

        return formatSelection;
    }

    private YoutubeDLRequest request(String videoUrl, String directory){

        YoutubeDLRequest request = new YoutubeDLRequest(videoUrl, directory);
        request.setOption("ffmpeg-location", "D:\\LIBS-PATH\\ffmpeg.exe");

        // 1. Simular un navegador moderno (Chrome en Windows 10/11)
//        String chromeUserAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36";
//        request.setOption("user-agent", chromeUserAgent);

        // 2. Agregar el Referer (la misma URL del video suele servir)
        // Esto evita el error "403 Forbidden" en muchos sitios
        request.setOption("referer", videoUrl);

        // 3. Opciones de sigilo adicionales
        request.setOption("no-check-certificate"); // Salta errores de SSL si el sitio es viejo
        request.setOption("geo-bypass");           // Intenta saltar bloqueos regionales




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
