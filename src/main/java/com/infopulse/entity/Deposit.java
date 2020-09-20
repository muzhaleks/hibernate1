package com.infopulse.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "deposits")
public class Deposit {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name="increment", strategy = "increment")
    private Long id;

    @Column(name="dep")
    private BigDecimal dep;

    @OneToMany(mappedBy = "deposit", fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    List<CustomerDeposit> customerDeposit = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getDep() {
        return dep;
    }

    public void setDep(BigDecimal dep) {
        this.dep = dep;
    }

    public List<CustomerDeposit> getCustomerDeposit() {
        return customerDeposit;
    }

    public void setCustomerDeposit(List<CustomerDeposit> customerDeposit) {
        this.customerDeposit = customerDeposit;
    }
}
