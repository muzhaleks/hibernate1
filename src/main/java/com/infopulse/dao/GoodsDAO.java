package com.infopulse.dao;

import com.infopulse.entity.Goods;
import com.infopulse.entity.OtherGoods;

public interface GoodsDAO {
    void insertGoods(Goods goods);

    void insertOtherGoods(OtherGoods otherGoods);

}
