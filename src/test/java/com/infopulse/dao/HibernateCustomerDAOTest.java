package com.infopulse.dao;

import com.infopulse.entity.*;
import com.infopulse.factory.Factory;
import org.junit.Test;

import java.util.ArrayList;
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
        order1.setCustomer(customer);

        Order order2 =new Order();
        order2.setName("jjjj");
        order2.setCustomer(customer);

        customer.addOrder(order1);
        customer.addOrder(order2);

        Phone phone = new Phone();
        phone.setPhoneNumber("555666777");
        phone.setCustomer(customer);

        customer.setPhone(phone);

        Bank bank = new Bank();
        bank.setName("Private");
        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);
        bank.setCustomers(customerList);

        customer.setBanks(new ArrayList<Bank>(){{add(bank);}});

        customerDAO.insertCustomer(customer);

        //new customer
        GoodCustomer goodCustomer = new GoodCustomer();
        goodCustomer.setDiscount(20);
        goodCustomer.setName("Petya");
        goodCustomer.setSurename("Pupkin");
        goodCustomer.setAddress(address);

        Bank newBank = new Bank();
        newBank.setName("Private");
        List<Customer> newCustomerList = new ArrayList<>();
        customerList.add(goodCustomer);
        newBank.setCustomers(newCustomerList);

        goodCustomer.setBanks(new ArrayList<Bank>(){{add(newBank);}});

        customerDAO.insertCustomer(goodCustomer);


        //new customer
        GoodCustomer customer2 = new GoodCustomer();
        customer2.setDiscount(20);
        customer2.setName("Petya");
        customer2.setSurename("Pupkin");
        customer2.setAddress(address);

        BadBank newBank2 = new BadBank();
        newBank2.setBlackList("jjjjjjjjjj");
        newBank2.setName("Private");
        List<Customer> newCustomerList2 = new ArrayList<>();
        customerList.add(customer2);
        newBank2.setCustomers(newCustomerList2);

        customer2.setBanks(new ArrayList<Bank>(){{add(newBank2);}});

        customerDAO.insertCustomer(customer2);

        List<Customer> customers = customerDAO.getAllCustomers();
//        customers.get(0).getOrders().get(0).getName(); //error in the case of LAZY.

        Customer customerResult = customers.stream()
                                           .filter(c -> c.getName().equals("Vasya"))
                                           .findFirst()
                                           .get();

        Goods goods = new Goods();
        goods.setCode("1234");
        goods.setCountry("Ukraine");
        goods.setName("tttt");

        GoodsDAO goodsDAO = instance.getGoodsDAO();

        goodsDAO.insertGoods(goods);

        OtherCompositeKey key = new OtherCompositeKey();
        key.setCode("6789");
        key.setCountry("USA");

        OtherGoods otherGoods = new OtherGoods();
        otherGoods.setCompositeKey(key);
        otherGoods.setName("pppp");

        GoodsDAO otherGoodsDAO = instance.getGoodsDAO();
        otherGoodsDAO.insertOtherGoods(otherGoods);

        assertEquals("Vasya", customerResult.getName());
        assertEquals("Pupkin", customerResult.getSurename());

    }
}
