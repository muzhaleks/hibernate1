package com.infopulse.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.DiscriminatorType.STRING;
import static javax.persistence.InheritanceType.TABLE_PER_CLASS;

@Setter
@Getter
@NoArgsConstructor
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "myregion")
@Entity
@Table(name="customers")
@Inheritance(strategy=TABLE_PER_CLASS)
//@DynamicUpdate
//@DiscriminatorColumn(name="Typecli", discriminatorType=STRING, length=20)
//@DiscriminatorValue("CUSTOMER")
public class Customer {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private Long id;

    @Column(name="name",nullable = false, length = 100)
    @Basic
    private String name;

    @Column(name="key", unique = true)
    private String key;

    @Column(name="surename", unique = false, nullable = false, length = 120)
    @Basic
    private String surename;

    @Formula("concat(name, '-', surename)")
    private String nameSurname;//name-surename

    @Type(type = "com.infopulse.entity.type.AddressType")
    private Address address;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    List<Order> orders = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    Phone phone;

    @ManyToMany(mappedBy = "customers", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    List<Bank> banks = new ArrayList<>();

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<CustomerDeposit> customerDeposits = new ArrayList<>();

    public void addOrder(Order order){
        orders.add(order);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSurename() {
        return surename;
    }

    public void setSurename(String surename) {
        this.surename = surename;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public List<Bank> getBanks() {
        return banks;
    }

    public void setBanks(List<Bank> banks) {
        this.banks = banks;
    }

    public List<CustomerDeposit> getCustomerDeposits() {
        return customerDeposits;
    }

    public void setCustomerDeposits(List<CustomerDeposit> customerDeposits) {
        this.customerDeposits = customerDeposits;
    }

    @PostPersist
    @PostLoad
    public void onSave(){
        if(key == null){
            key = id.toString();
        }
    }
}
