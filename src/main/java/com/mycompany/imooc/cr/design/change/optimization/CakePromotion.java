package com.mycompany.imooc.cr.design.change.optimization;

import com.mycompany.imooc.cr.design.change.Cake;

public class CakePromotion {

    public int getCakePrice(int cakeId, SaleStrategy saleStrategy) {
        Cake cake = getCake(cakeId);
        return saleStrategy.getSalePrice(cake);
    }

    private Cake getCake(int cakeId) {
        return new Cake();
    }

}
