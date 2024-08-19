package com.example.BookstoreAPI.controllers;


import com.example.BookstoreAPI.models.Customer;
import com.example.BookstoreAPI.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.Optional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    List<Customer> customers = new ArrayList<>();
    @Autowired
    private CustomerRepository customerRepository;

    // POST endpoint to create a new customer with JSON request body
    @PostMapping("/json")
    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody Customer customer) {
        Customer savedCustomer = customerRepository.save(customer);
        customers.add(savedCustomer);
        return ResponseEntity.ok(savedCustomer);
    }
    @GetMapping("/json")
    public ResponseEntity<List<Customer>> getCustomers() {

        return ResponseEntity.ok(customers);
    }
    // GET method to fetch a customer by ID
    @GetMapping("/json/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        // Find the customer with the specified ID
        Optional<Customer> customer = customers.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();

        // Return the customer if found, otherwise return a 404 Not Found
        return customer.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    // POST endpoint to create a new customer with form data
    @PostMapping("/form")
    public ResponseEntity<Map<String, String>> createCustomerWithFormData(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("email") String email,
            @RequestParam("password") String password) {

        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setPassword(password);

        customerRepository.save(customer);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Customer created successfully");
        response.put("customerId", String.valueOf(customer.getId()));

        return ResponseEntity.ok(response);
    }
}
