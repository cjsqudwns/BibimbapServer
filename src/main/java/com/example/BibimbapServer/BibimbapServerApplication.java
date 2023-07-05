package com.example.BibimbapServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BibimbapServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BibimbapServerApplication.class, args);
	}

}
