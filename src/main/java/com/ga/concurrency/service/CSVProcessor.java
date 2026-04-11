package com.ga.concurrency.service;

import com.ga.concurrency.model.Employee;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CSVProcessor implements CommandLineRunner {

    private final EmployeeCsvReader employeeCsvReader;
    private final EmployeeCsvWriter employeeCsvWriter;
    private final ConcurrentEmployeeProcessor concurrentEmployeeProcessor;


    // @Value is from application.propertiess config
    @Value("${app.csv.input:test_employees.csv}")
    private String inputCSV;

    @Value("${app.csv.output:processed_employees.csv}")
    private String outputCSV;

    @Value("${app.pool.size:4}")
    private int poolSize;

    @Value("${app.semaphore.maxConnections:3}")
    private int maxConnections;

    public CSVProcessor(EmployeeCsvReader employeeCsvReader,
                        EmployeeCsvWriter employeeCsvWriter,
                        ConcurrentEmployeeProcessor concurrentEmployeeProcessor) {

        this.employeeCsvReader = employeeCsvReader;
        this.employeeCsvWriter = employeeCsvWriter;
        this.concurrentEmployeeProcessor = concurrentEmployeeProcessor;

    }

    // read csv -> concurrent process -> write processed csv
    public List<Employee> process() {
        List<Employee> employees = employeeCsvReader.readEmployeesFromCSV(inputCSV);

        List<Employee> processedEmployees = concurrentEmployeeProcessor.processEmployees(employees, poolSize, maxConnections);

        employeeCsvWriter.writeEmployeesToCSV(outputCSV, processedEmployees);
        return processedEmployees;
    }

    // Spring Boot runs this on startup
    @Override
    public void run(String... args) {
        List<Employee> processed = process();
        System.out.println("CSV processing completed. Employees processed: " + processed.size());
    }

}
