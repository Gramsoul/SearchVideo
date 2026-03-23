package com.app.alpha;

import com.app.alpha.Clases.DLP;
import com.app.alpha.Interfaces.Downloadeable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class AlphaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlphaApplication.class, args);

        Downloadeable dlp = new DLP();
        //dlp.download("");

	}

}
