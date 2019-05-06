package com.tqs1.tqs1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Tqs1Application {

	private static final Logger log = LoggerFactory.getLogger(Tqs1Application.class);

	public static void main(String args[]) {
		SpringApplication.run(Tqs1Application.class);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate, WeatherRepository repository, DataRepository repository2, DailyRepository repository3)throws Exception {
		return args -> {
			Weather weather = restTemplate.getForObject(
					"https://api.darksky.net/forecast/28a3c3fed03114a110538615e5262cbb/37.8267,-122.4233", Weather.class);
			log.info(weather.toString());
			for (Data d : weather.getDaily().getData())
				repository2.save(d);
			repository3.save(weather.getDaily());
			repository.save(weather);
		};
	}

	/*@Bean
	public CommandLineRunner demo(WeatherRepository repository) {
		return (args) -> {
			// save a weather
			repository.save(new Weather(37.8267, -122.4233, "America/Los_Angeles", new Currently(1556985135, "Mostly Cloudy", "partly-cloudy-day")));

			// fetch all weathers
			log.info("Weather found with findAll():");
			log.info("-------------------------------");
			for (Weather weather : repository.findAll()) {
				log.info(weather.toString());
			}
			log.info("");

			// fetch an individual weather by ID
			repository.findById(1L)
					.ifPresent(weather -> {
						log.info("Weather found with findById(1L):");
						log.info("--------------------------------");
						log.info(weather.toString());
						log.info("");
					});

			// fetch weather by timezone
			log.info("Customer found with findByTimezone('America/New_York'):");
			log.info("--------------------------------------------");
			repository.findByTimezone("America/New_York").forEach(weather -> {
				log.info(weather.toString());
			});
			// for (Customer bauer : repository.findByLastName("Bauer")) {
			// 	log.info(bauer.toString());
			// }
			log.info("");
		};
	}*/
}