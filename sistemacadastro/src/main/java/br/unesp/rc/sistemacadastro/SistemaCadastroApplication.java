package br.unesp.rc.sistemacadastro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SistemaCadastroApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaCadastroApplication.class, args);
	}

}
