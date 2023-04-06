package com.hello.hello;

// import java.util.concurrent.CompletableFuture;
// import java.util.concurrent.ExecutionException;

// // import org.springframework.boot.SpringApplication;
// // import org.springframework.boot.autoconfigure.SpringBootApplication;

// // @SpringBootApplication
// // public class HelloApplication {

// // 	public static void main(String[] args) {
// // 		SpringApplication.run(HelloApplication.class, args);
// // 	}

// // }

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.SpringApplication;
// import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.ComponentScan;
// import org.springframework.scheduling.annotation.Async;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.client.RestTemplate;

// @SpringBootApplication
// @RestController
// @ComponentScan(basePackages = "com.example.demo")
// public class HelloApplication {

// 	public static void main(String[] args) {
// 		SpringApplication.run(HelloApplication.class, args);
// 	}

// 	@Bean
// 	public RestTemplate restTemplate() {
// 		return new RestTemplate();
// 	}

// 	@RestController
// 	public class FrontendController {

// 		@Autowired
// 		private RestTemplate restTemplate;

// 		@GetMapping("/")
// 		public String getCombinedMessage() throws InterruptedException, ExecutionException {

// 			String sync1Message = restTemplate.getForObject("http://192.168.29.20:8081/api/data", String.class);
// 			String sync2Message = restTemplate.getForObject("http://192.168.29.20:8082/api/data", String.class);
// 			CompletableFuture<String> asyncMessageFuture = getAsyncMessage();
// 			return sync1Message + " " + sync2Message + " " + asyncMessageFuture.get();
// 		}

// 		@GetMapping("/hello")
// 		public String hello() {
// 			return "testing";
// 		}

// 		@Async
// 		public CompletableFuture<String> getAsyncMessage() {
// 			String asyncMessage = restTemplate.getForObject("http://192.168.29.20:8083/data", String.class);
// 			return CompletableFuture.completedFuture(asyncMessage);
// 		}
// 	}

// }
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
@ComponentScan(basePackages = "com.example.demo")
public class HelloApplication {

	@Value("${sync1.url}")
	private String sync1Url;

	@Value("${sync2.url}")
	private String sync2Url;

	@Value("${async.url}")
	private String asyncUrl;

	public static void main(String[] args) {
		SpringApplication.run(HelloApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@RestController
	public class FrontendController {

		@Autowired
		private RestTemplate restTemplate;

		@GetMapping("/")
		public String getCombinedMessage() throws InterruptedException, ExecutionException {

			String sync1Message = restTemplate.getForObject(sync1Url, String.class);
			String sync2Message = restTemplate.getForObject(sync2Url, String.class);
			CompletableFuture<String> asyncMessageFuture = getAsyncMessage();
			return sync1Message + " " + sync2Message + " " + asyncMessageFuture.get();
		}

		@GetMapping("/hello")
		public String hello() {
			return "testing";
		}

		@Async
		public CompletableFuture<String> getAsyncMessage() {
			String asyncMessage = restTemplate.getForObject(asyncUrl, String.class);
			return CompletableFuture.completedFuture(asyncMessage);
		}
	}

}
