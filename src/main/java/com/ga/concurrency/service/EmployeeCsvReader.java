package com.ga.concurrency.service;

import com.ga.concurrency.model.Employee;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeCsvReader {

    public List<Employee> readEmployeesFromCSV(String fileName) {
        List<Employee> employees = new ArrayList<>();

        try {
            InputStream inputStream = EmployeeCsvReader.class.getClassLoader().getResourceAsStream(fileName);
            if (inputStream == null) throw new RuntimeException("File not found: " + fileName);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                if (line.length() == 0) continue;

                // seperate the fields by comma
                String[] fields = line.split(",");

                // create new employee obj
                Employee employee = new Employee();

                // Set data based by seperated fields + trim
                employee.setId(Long.parseLong(fields[0].trim()));
                employee.setName(fields[1].trim());
                employee.setSalary(Double.parseDouble(fields[2].trim()));
                employee.setJoinDate(LocalDate.parse(fields[3].trim()));
                employee.setRole(fields[4].trim());
                employee.setProjectCompletionPercentage(Double.parseDouble(fields[5].trim()));

                // add employee to employees arraylist
                employees.add(employee);
            }

            bufferedReader.close();
            return employees;
        } catch (Exception ex) {
            throw new RuntimeException("Error reading/parsing employees", ex);
        }

    }
}
