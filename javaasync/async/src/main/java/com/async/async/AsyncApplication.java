package com.async.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication
@ComponentScan("com.async.async")
public class AsyncApplication {

	public static void main(String[] args) {
		SpringApplication.run(AsyncApplication.class, args);
	}

	private ExecutorService executorService = Executors.newFixedThreadPool(10);

	@Component
	public class AsyncService {
		public CompletableFuture<String> asyncHello() {
			return CompletableFuture.supplyAsync(() -> {
				// Perform some async task here...
				try {
					Thread.sleep(5000); // Simulate a long-running task
					// return "Hello from the async container!";
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return "Hello from Async container!";
			}, executorService);
		}
	}

	@RestController
	public class AsyncController {
		@Autowired
		private AsyncService asyncService;

		@GetMapping("/data")
		public CompletableFuture<String> asyncHello() {
			return asyncService.asyncHello();
		}
	}
}
