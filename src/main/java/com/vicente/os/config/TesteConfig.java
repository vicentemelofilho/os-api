package com.vicente.os.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.vicente.os.services.DBService;

@Configuration
@Profile("test")
public class TesteConfig {
	@Autowired
	private DBService dbService;
	
	@Bean
	public Boolean instanciaDB() {
		dbService.instanciaDB();
		
		return true; 
	}

}
