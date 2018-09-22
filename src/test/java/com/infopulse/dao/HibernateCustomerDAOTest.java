package com.infopulse.dao;

import com.infopulse.entity.Address;
import com.infopulse.entity.Customer;
import com.infopulse.entity.Order;
import com.infopulse.factory.Factory;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class HibernateCustomerDAOTest {

    private Factory instance = Factory.getInstance();


    @Test
    public void  insertAndSelectEntityTest(){
        CustomerDAO customerDAO = instance.getCustomerDAO();
        customerDAO.deleteAll();

        Customer customer = new Customer();
        customer.setName("Vasya");
        customer.setSurename("Pupkin");
        Address address =new Address();
        address.setStreet("aaaaaaa");
        address.setCity("Kiev");
        customer.setAddress(address);

        Order order1 =new Order();
        order1.setName("ffff");

        Order order2 =new Order();
        order2.setName("jjjj");

        customer.addOrder(order1);
        customer.addOrder(order2);


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
