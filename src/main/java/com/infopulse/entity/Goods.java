package com.infopulse.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name="goods")
@IdClass(value = CompositeKey.class)
public class Goods {
    @Id
    @Column(name="code")
    String code;

    @Id
    @Column(name="country")
    String country;

    @Column(name="name", unique = false, nullable = false)
    String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
