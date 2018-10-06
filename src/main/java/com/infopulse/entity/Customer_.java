package com.infopulse.entity;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Customer.class)
public abstract class Customer_ {

	public static volatile SingularAttribute<Customer, Address> address;
	public static volatile ListAttribute<Customer, CustomerDeposit> customerDeposits;
	public static volatile SingularAttribute<Customer, Phone> phone;
	public static volatile ListAttribute<Customer, Bank> banks;
	public static volatile SingularAttribute<Customer, String> name;
	public static volatile SingularAttribute<Customer, String> nameSurname;
	public static volatile ListAttribute<Customer, Order> orders;
	public static volatile SingularAttribute<Customer, Long> id;
	public static volatile SingularAttribute<Customer, String> surename;
	public static volatile SingularAttribute<Customer, String> key;

	public static final String ADDRESS = "address";
	public static final String CUSTOMER_DEPOSITS = "customerDeposits";
	public static final String PHONE = "phone";
	public static final String BANKS = "banks";
	public static final String NAME = "name";
	public static final String NAME_SURNAME = "nameSurname";
	public static final String ORDERS = "orders";
	public static final String ID = "id";
	public static final String SURENAME = "surename";
	public static final String KEY = "key";

}

