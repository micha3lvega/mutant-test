package co.com.mutants.amqp.messaging.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MutantsAmqpMessagingConfig {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
