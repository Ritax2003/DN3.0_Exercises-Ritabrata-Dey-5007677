package com.example.EmployeeManagementSystem.service;

import com.example.EmployeeManagementSystem.entity.Employee;
import com.example.EmployeeManagementSystem.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> findEmployeesByDepartment(String departmentName) {
        return employeeRepository.findByDepartment_Name(departmentName);
    }

    public Employee findEmployeeByEmail(String email) {
        return employeeRepository.findByEmail(email);
    }

    // Additional service methods as needed
}
