package com.app.alpha.Clases.DTO;

import com.sapher.youtubedl.mapper.VideoFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class InfoDTO {
    @NotBlank
    private String url;
    @NotBlank
    private String title;
    private String thumbnail;
    private String uploader;
    private String description;
    @NotNull
    private List<VideoFormat> formats;

}
