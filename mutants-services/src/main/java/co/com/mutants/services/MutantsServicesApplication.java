package co.com.mutants.services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class MutantsServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MutantsServicesApplication.class, args);
	}

}
