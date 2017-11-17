package com.rutvikpatel.jpa2db;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
* <h1>Main Method Of Project</h1>
* This is startup point of project.
*
* @author  RutvikPatel
* @version 1.0
* @since   20-07-2017
*/

@EnableAutoConfiguration
@SpringBootApplication
public class SpringDataJpaDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaDemoApplication.class, args);
	}
}