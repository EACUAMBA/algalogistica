package com.algaworks.algalogistica.commons;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // Diz ao spring que este é uma classe de configuracao, e passa a ser uma classe gerenciavel pelo Spring, e ela tera metodos que definem beans Spring
public class ModelMapperConfig {
	
	@Bean // Esta anotacao estamos dizendo que este metodos inicializa e configura um bean que sera gerenciado pelo Spring e sera gerenciadodo pelo Spring, aqui dizemos o Spring como inicial a dependencia, e onde precisamos de model mapper ele será injectado.
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}

}
