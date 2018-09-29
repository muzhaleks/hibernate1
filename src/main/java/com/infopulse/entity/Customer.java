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
@Entity
@Table(name="customers")
@Inheritance(strategy=TABLE_PER_CLASS)
@DynamicUpdate
//@DiscriminatorColumn(name="Typecli", discriminatorType=STRING, length=20)
//@DiscriminatorValue("CUSTOMER")
public class Customer {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private Long id;

    @Column(name="name", unique = false, nullable = false, length = 100)
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

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST}, orphanRemoval = true)
    Phone phone;

    @ManyToMany(mappedBy = "customers", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    List<Bank> banks = new ArrayList<>();

    public void addOrder(Order order){
        orders.add(order);
    }


    @PostPersist
    @PostLoad
    public void onSave(){
        if(key == null){
            key = id.toString();
        }
    }
}
