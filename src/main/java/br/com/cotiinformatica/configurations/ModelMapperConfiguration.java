package br.com.cotiinformatica.configurations;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfiguration {

	// Aqui você pode configurar o ModelMapper, se necessário.
	// Por exemplo, você pode definir mapeamentos personalizados entre entidades e
	// modelos de resposta.

	// Exemplo:
	// @Bean
	// public ModelMapper modelMapper() {
	// ModelMapper modelMapper = new ModelMapper();
	// // Configurações adicionais do ModelMapper, se necessário
	// return modelMapper;
	// }

	// Se você não precisa de configurações personalizadas, pode deixar vazio.
	
	@Bean
	ModelMapper getModelMapper() {
		var mapper = new ModelMapper();
		// TODO fazer configuracoes para o ModelMapper, se necessário
		return mapper;
	}
}
