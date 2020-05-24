package com.mycompany.imooc.cr.design.change;

public class CakePromotion {

    public int getCakePrice(int cakeId, String festival) {
        int originalPrice = getCake(cakeId).getPrice();
        if ("新年活动".equals(festival)) {
            int salePrice = 0;
            // 计算新年活动折扣
            // salePrice = xxx
            return salePrice;
        } else if ("中秋活动".equals(festival)) {
            int salePrice = 0;
            // 计算中秋活动折扣
            // salePrice = xxx
            return salePrice;
        } else {
            return originalPrice;
        }
    }

    private Cake getCake(int cakeId) {
        return new Cake();
    }

}
