package com.example.BookstoreAPI.service;
import com.example.BookstoreAPI.models.Customer;
import java.util.List;
import java.util.Optional;
public interface CustomerService {

    List<Customer> getAllCustomers();

    Optional<Customer> getCustomerById(int id);

    Customer createCustomer(Customer customer);

    Customer updateCustomer(Customer customer);

    void deleteCustomer(int id);
}
