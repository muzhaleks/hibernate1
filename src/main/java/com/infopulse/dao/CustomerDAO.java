package com.infopulse.dao;

import com.infopulse.entity.Count;
import com.infopulse.entity.Customer;
import com.infopulse.entity.Phone;

import java.util.List;

public interface CustomerDAO {

    void insertCustomer(Customer customer);

    List<Customer> getAllCustomers();

    void deleteAll();

    Customer findCustomer(String name, String surename);

    void updateCustomer(Customer customer);

    long customers();

    Count numberCustomers();

    Customer findCustomerCriteria(String name, String surename);

    public List<String> findPhoneByCustomerName(String name);



}
