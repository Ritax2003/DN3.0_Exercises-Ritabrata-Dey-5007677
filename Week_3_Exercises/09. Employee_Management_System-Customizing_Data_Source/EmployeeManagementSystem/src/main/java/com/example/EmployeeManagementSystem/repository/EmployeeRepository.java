package com.example.EmployeeManagementSystem.repository;
import com.example.EmployeeManagementSystem.projection.*;
import com.example.EmployeeManagementSystem.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.EmployeeManagementSystem.dto.*;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Derived query method to find employees by name
    List<Employee> findByName(String name);
    // Derived query method to find employees by email
    Employee findByEmail(String email);
    // Pagination
    Page<Employee> findAll(Pageable pageable);
    List<EmployeeNameSalaryProjection> findByDepartmentName(String departmentName);
    // Sorting (automatically handled by Pageable)
    @Query("SELECT new com.example.EmployeeManagementSystem.dto.EmployeeNameSalaryDTO(e.name, e.salary) FROM Employee e WHERE e.department.name = :departmentName")
    List<EmployeeNameSalaryDTO> findEmployeeNameAndSalaryByDepartment(String departmentName);

}
