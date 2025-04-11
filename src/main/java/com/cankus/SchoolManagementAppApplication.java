package com.cankus;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SchoolManagementAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolManagementAppApplication.class, args);
	}


	// config package altında @Configuration annotation altında da oluşturulabilir
	@Bean
	public ModelMapper mapper(){
		return new ModelMapper();
	}

}
