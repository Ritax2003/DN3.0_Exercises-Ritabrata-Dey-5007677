package com.example.BookstoreAPI.controllers;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import com.example.BookstoreAPI.dto.CustomerDTO;
import com.example.BookstoreAPI.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
@RestController
@RequestMapping("/api/customers")
@Validated
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Operation(summary = "Create a new customer", description = "Create a new customer in the bookstore")
    @ApiResponse(responseCode = "201", description = "Customer created successfully")
    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(
            @Valid @RequestBody CustomerDTO customerDTO) {
        CustomerDTO createdCustomer = customerService.createCustomer(customerDTO);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    @Operation(summary = "Get a customer by ID", description = "Retrieve customer details by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer found"),
            @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(
            @Parameter(description = "ID of the customer to be retrieved") @PathVariable int id) {
        CustomerDTO customerDTO = customerService.getCustomerById(id);
        if (customerDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }

    @Operation(summary = "Get all customers", description = "Retrieve a list of all customers")
    @ApiResponse(responseCode = "200", description = "List of customers")
    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        List<CustomerDTO> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @Operation(summary = "Update an existing customer", description = "Update details of an existing customer")
    @ApiResponse(responseCode = "200", description = "Customer updated successfully")
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomer(
            @PathVariable int id,
            @Valid @RequestBody CustomerDTO customerDTO) {
        CustomerDTO updatedCustomer = customerService.updateCustomer(id, customerDTO);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

    @Operation(summary = "Delete a customer", description = "Delete a customer by their ID")
    @ApiResponse(responseCode = "204", description = "Customer deleted successfully")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable int id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}