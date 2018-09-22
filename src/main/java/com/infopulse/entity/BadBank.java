package com.infopulse.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "bad_bank")
@PrimaryKeyJoinColumn(name="BANK_ID")
public class BadBank extends Bank{

    @Column(name="black_list")
    private String blackList;
}
