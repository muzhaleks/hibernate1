package com.infopulse.dao;

import com.infopulse.entity.Bank;
import com.infopulse.entity.Customer;
import com.infopulse.entity.Deposit;
import com.infopulse.entity.Phone;

import java.math.BigDecimal;
import java.util.List;

public interface DepositDAO {
    List<Deposit> findDepositByCustomer(String name);

    void insertDeposit(Deposit deposit);

    List<Phone> findPhoneByDepositValue(BigDecimal dep);

    List<String> findPhoneAsStringByDepositValue(BigDecimal dep);
}
