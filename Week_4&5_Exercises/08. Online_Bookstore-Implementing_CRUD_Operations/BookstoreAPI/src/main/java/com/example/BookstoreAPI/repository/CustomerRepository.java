package com.example.BookstoreAPI.repository;

import com.example.BookstoreAPI.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
