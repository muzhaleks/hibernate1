package com.infopulse.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.InheritanceType.JOINED;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "banks")
@Inheritance(strategy=JOINED)
public class Bank {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private Long id;


    @Column(name = "name", unique = false, nullable = false, length = 100)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Customer> customers = new ArrayList<>();
}
