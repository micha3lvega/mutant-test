package co.com.mutants.amqp.messaging.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import co.com.mercado.libre.commons.dto.DNASequenceDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RabbitMQSender {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	@Value("${config.rabbitmq.exchange}")
	private String exchange;

	@Value("${config.rabbitmq.routingkey}")
	private String routingkey;

	@Async
	@Retryable(maxAttempts = 1, value = RuntimeException.class, backoff = @Backoff(value = 3000L))
	public void send(DNASequenceDto dnaSequenceDto) {
		log.trace("enviando nuevo mensaje: {}", dnaSequenceDto);
		rabbitTemplate.convertAndSend(exchange, routingkey, dnaSequenceDto);
	}

	@Recover
	void recover(RuntimeException e, DNASequenceDto dnaSequenceDto) {
		log.error("RuntimeException: {}, mensaje: {}", e.getMessage(), dnaSequenceDto, e);
	}

}
