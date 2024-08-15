package com.example.EmployeeManagementSystem.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class EmployeeBatchService {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void batchInsertEmployees(List<Employee> employees) {
        Session session = sessionFactory.getCurrentSession();
        int batchSize = 50;

        for (int i = 0; i < employees.size(); i++) {
            session.save(employees.get(i));

            if (i % batchSize == 0 && i > 0) {
                session.flush();
                session.clear();
            }
        }
    }
}
