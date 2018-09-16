package com.infopulse.main;

import com.infopulse.dao.CustomerDAO;
import com.infopulse.entity.Customer;
import com.infopulse.factory.Factory;

public class Main {

    public static void main(String[] args){
        Factory factory = Factory.getInstance();
        CustomerDAO customerDAO = factory.getCustomerDAO();
        Customer customer =new Customer();
        customer.setName("aaaaa");
        customer.setSurename("bbbbbb");
        customerDAO.insertCustomer(customer);
    }
}
