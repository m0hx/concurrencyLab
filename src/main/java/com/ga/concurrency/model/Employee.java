package com.ga.concurrency.model;

import java.time.LocalDate;

public class Employee {

    private long id;
    private String name;
    private double salary;
    private LocalDate joinDate;
    private String role;
    private double projectCompletionPercentage;
    private double incrementPercentage;
    private double newSalary;

    public Employee() {
    }

    public Employee(long id, String name, double salary, LocalDate joinDate, String role, double projectCompletionPercentage) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.joinDate = joinDate;
        this.role = role;
        this.projectCompletionPercentage = projectCompletionPercentage;
        this.incrementPercentage = 0.0;
        this.newSalary = 0.0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public double getProjectCompletionPercentage() {
        return projectCompletionPercentage;
    }

    public void setProjectCompletionPercentage(double projectCompletionPercentage) {
        this.projectCompletionPercentage = projectCompletionPercentage;
    }

    public double getIncrementPercentage() {
        return incrementPercentage;
    }

    public void setIncrementPercentage(double incrementPercentage) {
        this.incrementPercentage = incrementPercentage;
    }

    public double getNewSalary() {
        return newSalary;
    }

    public void setNewSalary(double newSalary) {
        this.newSalary = newSalary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", joinDate=" + joinDate +
                ", role='" + role + '\'' +
                ", projectCompletionPercentage=" + projectCompletionPercentage +
                ", incrementPercentage=" + incrementPercentage +
                ", newSalary=" + newSalary +
                '}';
    }
}
