package com.infopulse.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private Long id;


    @Column(name = "name", unique = false, nullable = false, length = 100)
    private String name;

    @ManyToOne
    @JoinColumn(name="mycustomer_id", referencedColumnName = "id")
    private Customer customer;

}
