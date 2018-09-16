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
@Table(name="customers")
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
}
