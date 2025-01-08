package com.umc.ttt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TttApplication {

	public static void main(String[] args) {
		SpringApplication.run(TttApplication.class, args);
	}

}
