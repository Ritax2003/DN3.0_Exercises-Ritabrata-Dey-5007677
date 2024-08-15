package com.example.EmployeeManagementSystem.repository;

import com.example.EmployeeManagementSystem.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department findByName(String name);
    Page<Department> findAll(Pageable pageable);
}
