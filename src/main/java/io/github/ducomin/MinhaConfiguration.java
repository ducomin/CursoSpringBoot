package io.github.ducomin;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinhaConfiguration {

	@Bean(name = "applicationName")
	public String applcationName() {
		return "Sistema de vendas";
	}

	@Bean(name = "outraConfiguracao")
	public String outraConfiguracao(){
		return "Sistema de Vendas";
	}
}
