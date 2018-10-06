package com.infopulse.dao;

import com.infopulse.entity.Customer;

import java.util.List;

public interface CustomerDAO {

    void insertCustomer(Customer customer);

    List<Customer> getAllCustomers();

    void deleteAll();

    Customer findCustomer(String name, String surename);

    void updateCustomer(Customer customer);



}
