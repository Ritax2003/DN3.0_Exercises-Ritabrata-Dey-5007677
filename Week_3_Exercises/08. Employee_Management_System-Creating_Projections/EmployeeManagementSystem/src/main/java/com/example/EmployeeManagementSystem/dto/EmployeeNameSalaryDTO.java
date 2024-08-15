package com.example.EmployeeManagementSystem.dto;

public class EmployeeNameSalaryDTO {
    private String name;
    private Double salary;

    public EmployeeNameSalaryDTO(String name, Double salary) {
        this.name = name;
        this.salary = salary;
    }

    // Getters and setters
    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Getter for salary
    public Double getSalary() {
        return salary;
    }

    // Setter for salary
    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
