package com.app.alpha;

import com.app.alpha.Clases.DLPService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class AlphaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlphaApplication.class, args);

        DLPService dlpService = new DLPService();
        //System.out.println(dlpService.info("https://www.youtube.com/watch?v=-Fgq-zFygDA"));


        //Video de test = https://www.youtube.com/watch?v=-Fgq-zFygDA

	}

}
