package com.chasel.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * springboot启动程序
 * 
 * @author street
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = { "com.chasel.**" })
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
