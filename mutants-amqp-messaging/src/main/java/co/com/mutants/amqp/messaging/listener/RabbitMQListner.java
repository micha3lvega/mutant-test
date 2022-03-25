package co.com.mutants.amqp.messaging.listener;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import co.com.mercado.libre.commons.dto.DNASequenceDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RabbitMQListner {

	private RestTemplate restTemplate;

	@Autowired
	private AmqpTemplate rabbitTemplate;

	@Value("${config.rabbitmq.exchange}")
	private String exchange;

	@Value("${config.rabbitmq.routingkey}")
	private String routingkey;

	public RabbitMQListner(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	/**
	 * Metoto que escucha los mensajes que llegan a la cola y luego consume el
	 * servicio de persistencia para guardar la nueva cadana de ADN
	 *
	 * @param message
	 */
	@Async
	@RabbitListener(queues = "${config.rabbitmq.queue.name}")
	@Retryable(maxAttempts = 2, value = RuntimeException.class, backoff = @Backoff(value = 3000L))
	public void onMessage(DNASequenceDto message) {

		log.info("Nuevo mensaje recibido: {}", message);
		restTemplate.postForObject("http://localhost:8081/persitence/api/v1/mutant", message, DNASequenceDto.class);

	}

	/**
	 * Metodo que se activa si los reintentos se terminan imprimiendo el error y
	 * luego añadiendolo nuevamente a la cola para reintentar
	 *
	 * @param e
	 * @param message
	 */
	@Recover
	void recover(RuntimeException e, DNASequenceDto message) {
		log.error("RuntimeException: {}, mensaje: {}", e.getMessage(), message, e);
		rabbitTemplate.convertAndSend(exchange, routingkey, message);
	}
}
