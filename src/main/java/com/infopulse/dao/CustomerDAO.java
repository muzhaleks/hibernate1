package com.infopulse.dao;

import com.infopulse.entity.Customer;

import java.util.List;

public interface CustomerDAO {

    void insertCustomer(Customer customer);

    List<Customer> getAllCustomers();
}
