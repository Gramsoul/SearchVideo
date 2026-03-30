package com.app.alpha.Clases;

import com.app.alpha.Clases.DTO.InfoDTO;
import com.fasterxml.jackson.core.io.JsonEOFException;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

@Controller
public class JsonService {

    private final ObjectMapper objectMapper;

    public JsonService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Bean
    public InfoDTO leerDatosJson(){
        try{
            InputStream inputStream = new ClassPathResource("JSON/test.json").getInputStream();
            return objectMapper.readValue(inputStream, InfoDTO.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
