package com.app.alpha;

import com.app.alpha.Clases.DLP;
import com.app.alpha.Interfaces.Downloadeable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.Map;

@EnableAsync
@SpringBootApplication
public class AlphaApplication {
	public static void main(String[] args) {
		SpringApplication.run(AlphaApplication.class, args);

//		DLP dlp = new DLP();

//	dlp.info("https://www.youtube.com/watch?v=xCPYONCRtBE&list=RDXM-wzcJN_E4&index=6")
//			.thenAccept(res -> {
//				System.out.println("---- respuesta ----");
//				res.forEach((k, v) -> {
//					System.out.println(k+": "+v);
//				});
//				System.out.println("---- --------- ----");
//			});
	}

}
