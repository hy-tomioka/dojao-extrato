package br.com.zup.extrato;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport
public class ExtratoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExtratoApplication.class, args);
	}

}
