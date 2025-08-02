package com.maestria.springmvcstock.service;
import com.maestria.springmvcstock.model.Customer;
import java.util.List;
public interface CustomerService {
    Customer createCustomer(Customer customer);
    Customer updateCustomer(Long id, Customer customerDetails);
    void deleteCustomer(Long id);
    List<Customer> getAllCustomers();
    List<Customer> searchCustomers(String query);
    Customer getCustomerById(Long id);
}