package br.com.cotiinformatica.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.cotiinformatica.infrastructure.filters.JwtAuthFilter;


@Configuration
public class FilterConfiguration {

	@Value("${jwt.secretkey}")
	private String jwtSecretkey;

	@Bean
	FilterRegistrationBean<JwtAuthFilter> jwtAuthFilter() {

		// Registrando o filter criado
		FilterRegistrationBean<JwtAuthFilter> registration = new FilterRegistrationBean<>();
		
		// Configurando a classe filter
		registration.setFilter(new JwtAuthFilter(jwtSecretkey));
		
		// Configurando os ednpoints que serão verificados pelo filter
		registration.addUrlPatterns("/api/*");
		
		// Retornando o filter
		return registration;
	}
	
}
