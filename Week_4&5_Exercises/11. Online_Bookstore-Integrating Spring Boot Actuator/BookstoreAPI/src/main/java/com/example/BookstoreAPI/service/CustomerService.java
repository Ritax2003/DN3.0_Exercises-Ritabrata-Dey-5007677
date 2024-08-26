package com.example.BookstoreAPI.service;
import com.example.BookstoreAPI.dto.CustomerDTO;
import java.util.List;
import java.util.Optional;
public interface CustomerService {
    CustomerDTO createCustomer(CustomerDTO customerDTO);
    CustomerDTO getCustomerById(int id);
    List<CustomerDTO> getAllCustomers();
    CustomerDTO updateCustomer(int id, CustomerDTO customerDTO);
    void deleteCustomer(int id);
}
