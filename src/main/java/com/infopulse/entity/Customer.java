package com.infopulse.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
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

    @Column(name="surename", unique = false, nullable = false, length = 120)
    @Basic
    private String surename;

    @Type(type = "com.infopulse.entity.type.AddressType")
    private Address address;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    List<Order> orders = new ArrayList<>();

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    Phone phone;

    @ManyToMany(mappedBy = "customers", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Bank> banks = new ArrayList<>();

    public void addOrder(Order order){
        orders.add(order);
    }
}
