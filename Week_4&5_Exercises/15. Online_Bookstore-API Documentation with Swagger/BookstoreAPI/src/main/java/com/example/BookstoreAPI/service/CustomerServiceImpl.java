package com.example.BookstoreAPI.service;

import com.example.BookstoreAPI.dto.CustomerDTO;
import com.example.BookstoreAPI.models.Customer;
import com.example.BookstoreAPI.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        Customer savedCustomer = customerRepository.save(customer);
        return modelMapper.map(savedCustomer, CustomerDTO.class);
    }

    @Override
    public CustomerDTO getCustomerById(int id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        return customerOptional.map(customer -> modelMapper.map(customer, CustomerDTO.class)).orElse(null);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(customer -> modelMapper.map(customer, CustomerDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO updateCustomer(int id, CustomerDTO customerDTO) {
        if (!customerRepository.existsById(id)) {
            return null;
        }
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        customer.setId(id); // Ensure the ID is set for the update
        Customer updatedCustomer = customerRepository.save(customer);
        return modelMapper.map(updatedCustomer, CustomerDTO.class);
    }

    @Override
    public void deleteCustomer(int id) {
        customerRepository.deleteById(id);
    }
}
