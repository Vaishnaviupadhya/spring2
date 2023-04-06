package com.sync2.sync2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Sync2Application {

	public static void main(String[] args) {
		SpringApplication.run(Sync2Application.class, args);
	}

	@RestController
	public class SyncController {

		@GetMapping("/api/data")
		public String getData() {
			return "Sync container 2 data";
		}
	}

}
