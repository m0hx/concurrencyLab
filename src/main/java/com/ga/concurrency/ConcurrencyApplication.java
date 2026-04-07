package com.ga.concurrency;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//test
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import com.ga.concurrency.service.EmployeeCsvReader;

@SpringBootApplication
public class ConcurrencyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConcurrencyApplication.class, args);
	}

	// test read employees from csv
	@Bean
	CommandLineRunner demo(EmployeeCsvReader employeeCsvReader) {
		return args -> {
			var employees = employeeCsvReader.readEmployeesFromCSV("test_employees.csv");
			System.out.println("Loaded employees = " + employees.size());
			System.out.println("First employee = " + employees.get(0));
			System.out.println("Second employee = " + employees.get(1));
		};
	}

}
