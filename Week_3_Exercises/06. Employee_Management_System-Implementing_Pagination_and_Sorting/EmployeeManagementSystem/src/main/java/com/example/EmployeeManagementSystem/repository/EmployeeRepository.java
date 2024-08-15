package com.example.EmployeeManagementSystem.repository;

import com.example.EmployeeManagementSystem.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Derived query method to find employees by name
    List<Employee> findByName(String name);

    // Derived query method to find employees by department name
    List<Employee> findByDepartmentName(String departmentName);

    // Derived query method to find employees by email
    Employee findByEmail(String email);
    // Pagination
    Page<Employee> findAll(Pageable pageable);

    // Sorting (automatically handled by Pageable)
}
