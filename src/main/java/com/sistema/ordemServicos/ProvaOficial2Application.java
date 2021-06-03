package com.sistema.ordemServicos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class ProvaOficial2Application {

	public static void main(String[] args) {
		SpringApplication.run(ProvaOficial2Application.class, args);
	}

	@RequestMapping("/")
	public String index(){
		return "index";
	}

}
