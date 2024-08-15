package com.example.EmployeeManagementSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@SpringBootApplication
public class EmployeeManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementSystemApplication.class, args);
	}

    @Repository
    public static interface EmployeeRepository extends JpaRepository<Employee, Long> {

        // Query method based on method name
        List<Employee> findByLastName(String lastName);

        // Query method for finding employees by department
        List<Employee> findByDepartmentName(String departmentName);

        // Query method to find employees with a salary greater than a specified amount
        List<Employee> findBySalaryGreaterThan(Double salary);
    }
}
