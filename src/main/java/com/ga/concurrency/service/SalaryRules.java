package com.ga.concurrency.service;

import com.ga.concurrency.model.Employee;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class SalaryRules {

    public void applyRaise(Employee employee) {
        LocalDate today = LocalDate.now();

        // If project completion percentage is less than 60%, no increment.
        if (employee.getProjectCompletionPercentage() < 0.60) {
            employee.setIncrementPercentage(0.0);
            employee.setNewSalary(employee.getSalary());
            return;
        }

        double rolePercent = rolePercentage(employee.getRole());

        // Calculate the number of years worked (+2% per year only if at least one full year).
        long years = ChronoUnit.YEARS.between(employee.getJoinDate(), today);
        double yearsPercentage = (years >= 1) ? years * 2.0 : 0.0;

        double totalPercentage = rolePercent + yearsPercentage;

        double newSalary = employee.getSalary() + (employee.getSalary() * totalPercentage / 100.0);
        employee.setIncrementPercentage(totalPercentage);
        employee.setNewSalary(newSalary);

    }

    private double rolePercentage(String role) {
        String r = (role == null) ? "" : role.trim().toLowerCase();

        // Roles: Director, Manager, Employee.
        // Director receives 5% increase,
        // Manager receives 2% increase.
        // Employee receives 1% increase.

        return switch(r) {
            case "director" -> 5.0;
            case "manager" -> 2.0;
            case "employee" -> 1.0;
            default -> 0.0;
        };

    }


}
