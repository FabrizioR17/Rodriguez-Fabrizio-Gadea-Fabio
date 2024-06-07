package com.backend.clinicaOdontologica;

import com.backend.clinicaOdontologica.dbconnection.H2Connection;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

//@SpringBootApplication
//public class ClinicaOdontologicaApplication {
//
//	public static void main(String[] args) {
//		H2Connection.ejecutarScriptInicial();
//	}
//
//}

@SpringBootApplication
public class ClinicaOdontologicaApplication {

	public static void main(String[] args) {

		H2Connection.ejecutarScriptInicial();

		SpringApplication.run(ClinicaOdontologicaApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
}