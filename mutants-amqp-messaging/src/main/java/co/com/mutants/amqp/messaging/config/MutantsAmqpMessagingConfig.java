package co.com.mutants.amqp.messaging.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;

@EnableRetry
@EnableAsync
@Configuration
public class MutantsAmqpMessagingConfig {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
