package com.sparta.myblogbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing //이거 꼭 달아야 함
@SpringBootApplication(exclude = SecurityAutoConfiguration.class) // Spring Security 인증 기능 제외
public class MyblogbackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyblogbackendApplication.class, args);
	}

}
