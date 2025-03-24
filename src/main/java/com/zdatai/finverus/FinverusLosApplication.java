package com.zdatai.finverus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.zdatai.finverus.repository")
@EnableTransactionManagement
@EnableCaching
public class FinverusLosApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinverusLosApplication.class, args);
	}

}
