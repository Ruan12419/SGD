package com.GerenciadorDormitorio.SGD;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@RestController
public class SgdApplication {

	public static void main(String[] args) {
		SpringApplication.run(SgdApplication.class, args);
	}

}
