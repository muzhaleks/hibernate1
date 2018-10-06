package com.infopulse.entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Phone.class)
public class Phone_ {
    public static volatile SingularAttribute<Phone, String> phoneNumber;
    public static volatile SingularAttribute<Phone, Long> id;
    public static volatile SingularAttribute<Phone, Customer> customer;

    public static final String PHONE_NUMBER = "phoneNumber";
    public static final String ID = "id";
    public static final String CUSTOMER = "customer";
}
