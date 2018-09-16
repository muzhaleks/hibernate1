package com.infopulse.dao;

import com.infopulse.entity.Customer;
import com.infopulse.factory.Factory;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class HibernateCustomerDAOTest {

    private Factory instance = Factory.getInstance();


    @Test
    public void  insertAndSelectEntityTest(){
        Customer customer = new Customer();
        customer.setName("Vasya");
        customer.setSurename("Pupkin");

        CustomerDAO customerDAO = instance.getCustomerDAO();

        customerDAO.insertCustomer(customer);

        List<Customer> customers = customerDAO.getAllCustomers();

        Customer customerResult = customers.stream()
                                           .filter(c -> c.getName().equals("Vasya"))
                                           .findFirst()
                                           .get();

        assertEquals("Vasya", customerResult.getName());
        assertEquals("Pupkin", customerResult.getSurename());

    }
}
