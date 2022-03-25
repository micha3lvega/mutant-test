package co.com.mutants.amqp.messaging.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.mercado.libre.commons.dto.DNASequenceDto;
import co.com.mutants.amqp.messaging.sender.RabbitMQSender;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/queue/api/v1")
public class RabbitMQWebController {

	@Autowired
	private RabbitMQSender rabbitMQSender;

	@PostMapping
	public void producer(@RequestBody DNASequenceDto dna) {

		log.trace("nuevo mensaje: {}", dna);
		rabbitMQSender.send(dna);

	}

}
