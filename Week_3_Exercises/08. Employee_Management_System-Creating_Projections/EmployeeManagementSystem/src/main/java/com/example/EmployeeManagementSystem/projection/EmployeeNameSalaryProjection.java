package com.example.EmployeeManagementSystem.projection;
import org.springframework.beans.factory.annotation.Value;
public interface EmployeeNameSalaryProjection {
    @Value("#{target.firstName + ' ' + target.lastName}")
    String getName();
    Double getSalary();
}
