package com.erbli.tourplanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class TourplannerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TourplannerApplication.class, args);
		System.out.println("Hello");
	}

	@GetMapping("/")
	public String apiRoot() {
		return "Hello World";
	}
}
