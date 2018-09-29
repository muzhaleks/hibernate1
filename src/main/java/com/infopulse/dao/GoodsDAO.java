package com.infopulse.dao;

import com.infopulse.entity.Goods;
import com.infopulse.entity.OtherGoods;
import com.infopulse.entity.ThirdGoods;

public interface GoodsDAO {
    void insertGoods(Goods goods);

    void insertOtherGoods(OtherGoods otherGoods);

    void insertThirdGoods(ThirdGoods thirdGoods);

}
