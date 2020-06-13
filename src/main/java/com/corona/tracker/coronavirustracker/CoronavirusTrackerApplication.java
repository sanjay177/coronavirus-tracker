package com.corona.tracker.coronavirustracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CoronavirusTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoronavirusTrackerApplication.class, args);
	}

}
