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
@Table(name="third_goods", uniqueConstraints = @UniqueConstraint(columnNames = {"code", "country"}))
public class ThirdGoods {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment", strategy = "increment")
    Long id;

    @Column(name="code")
    String code;


    @Column(name="country")
    String country;

    @Column(name="name", unique = false, nullable = false)
    String name;
}
