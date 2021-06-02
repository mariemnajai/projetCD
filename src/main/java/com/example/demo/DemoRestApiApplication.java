package com.example.demo;

import java.util.Arrays;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.example.demo.domain.Employee;
import com.example.demo.repository.EmployeeRepository;

@SpringBootApplication
public class DemoRestApiApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(DemoRestApiApplication.class, args);
		EmployeeRepository employeeRepository = context.getBean(EmployeeRepository.class);

		Stream.of("ali", "bitar", "olfa", "samia", "modher",
				"moez", "rim", "souad", "bachir", "salah", "takoua",
				"mourad", "tasnim ", "badr")
				.forEach(s -> employeeRepository.save(new Employee(s)));

		employeeRepository.findAll().forEach(s -> System.out.println(s.getName()));
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {

			System.out.println("Let's inspect the beans provided by Spring Boot:");

			String[] beanNames = ctx.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for (String beanName : beanNames) {
				System.out.println(beanName);
			}

		};
	}

}
