package com.infopulse.dao;

import com.infopulse.entity.Bank;
import com.infopulse.entity.Customer;
import com.infopulse.entity.Deposit;

import java.util.List;

public interface DepositDAO {
    List<Deposit> findDepositByCustomer(String name);

    void insertDeposit(Deposit deposit);
}
