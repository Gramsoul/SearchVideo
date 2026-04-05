package com.app.alpha.Clases.DTO;

import com.sapher.youtubedl.mapper.VideoFormat;
import lombok.Data;

import java.util.List;

@Data
public class InfoDTO {
    private String url;
    private String title;
    private String thumbnail;
    private String uploader;
    private String description;
    private List<VideoFormat> formats;

}
