package com.ga.concurrency.service;

import com.ga.concurrency.model.Employee;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

@Service
public class EmployeeCsvWriter {

    public void writeEmployeesToCSV(String filePath, List<Employee> employees) {

        try (PrintWriter pw = new PrintWriter(new FileWriter(filePath))) {
            pw.println("id,name,salary,joinDate,role,projectCompletionPercentage,incrementPercentage,newSalary");
            for (Employee employee : employees) {
                pw.println(
                    employee.getId() + "," +
                    employee.getName() + "," +
                    employee.getSalary() + "," +
                    employee.getJoinDate() + "," +
                    employee.getRole() + "," +
                    employee.getProjectCompletionPercentage() + "," +
                    employee.getIncrementPercentage() + "," +
                    employee.getNewSalary()
                );
            }
        } catch (Exception ex) {
            throw new RuntimeException("Error writing CSV file: " + filePath, ex);
        }
    }
}
