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

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customers")
@Validated
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<EntityModel<CustomerDTO>> createCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        CustomerDTO createdCustomer = customerService.createCustomer(customerDTO);
        EntityModel<CustomerDTO> resource = EntityModel.of(createdCustomer);
        resource.add(linkTo(methodOn(CustomerController.class).getCustomerById(createdCustomer.getId())).withSelfRel());
        return new ResponseEntity<>(resource, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<CustomerDTO>> getCustomerById(@PathVariable int id) {
        CustomerDTO customerDTO = customerService.getCustomerById(id);
        if (customerDTO == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        EntityModel<CustomerDTO> resource = EntityModel.of(customerDTO);
        resource.add(linkTo(methodOn(CustomerController.class).getCustomerById(id)).withSelfRel());
        resource.add(linkTo(methodOn(CustomerController.class).getAllCustomers()).withRel("all-customers"));
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EntityModel<CustomerDTO>>> getAllCustomers() {
        List<CustomerDTO> customers = customerService.getAllCustomers();
        List<EntityModel<CustomerDTO>> resources = customers.stream()
                .map(customer -> EntityModel.of(customer,
                        linkTo(methodOn(CustomerController.class).getCustomerById(customer.getId())).withSelfRel()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<CustomerDTO>> updateCustomer(@PathVariable int id, @Valid @RequestBody CustomerDTO customerDTO) {
        CustomerDTO updatedCustomer = customerService.updateCustomer(id, customerDTO);
        if (updatedCustomer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        EntityModel<CustomerDTO> resource = EntityModel.of(updatedCustomer);
        resource.add(linkTo(methodOn(CustomerController.class).getCustomerById(id)).withSelfRel());
        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable int id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}