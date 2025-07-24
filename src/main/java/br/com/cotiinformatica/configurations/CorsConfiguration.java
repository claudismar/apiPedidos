package br.com.cotiinformatica.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		
		// definindo a política
		registry.addMapping("/**") // política aplicada para todos os endpoints
			.allowedOrigins("*") // permissão para qualquer domínio
			.allowedMethods("*") // permissão para qualquer método (POST, PUT, GET, etc)
			.allowedHeaders("*"); // permissão para enviar parametros no HEADER das requisições
	}

}
