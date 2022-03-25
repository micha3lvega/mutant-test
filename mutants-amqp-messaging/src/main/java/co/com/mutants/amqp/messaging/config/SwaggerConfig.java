package co.com.mutants.amqp.messaging.config;

import java.time.LocalDate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket designApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).enable(true).select()
				.apis(RequestHandlerSelectors.basePackage("co.com.mutants.amqp.messaging.controller"))
				.paths(PathSelectors.any()).build().pathMapping("/")
				.directModelSubstitute(LocalDate.class, String.class).genericModelSubstitutes(ResponseEntity.class)
				.useDefaultResponseMessages(false).enableUrlTemplating(true);
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Mutants Queue")
				.description("Proyecto encargado de administrar los mensajes entrantes y salientes de la cola")
				.version("1.0.0").build();
	}

}
