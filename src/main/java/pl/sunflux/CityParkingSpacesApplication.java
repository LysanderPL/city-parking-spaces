package pl.sunflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class CityParkingSpacesApplication {
	public static void main(String[] args) {
		SpringApplication.run(CityParkingSpacesApplication.class, args);
	}
}
