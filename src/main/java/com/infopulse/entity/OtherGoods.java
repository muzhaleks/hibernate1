package com.infopulse.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name="other_goods")
public class OtherGoods {

    @EmbeddedId
    OtherCompositeKey compositeKey;

    @Column(name="name", unique = false, nullable = false)
    String name;
}
