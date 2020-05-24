package com.mycompany.imooc.cr.complexity;

public class ReverseExpression {

    /**
     * 判断一个数是否为小于等于0，或者大于0的偶数
     *
     * @param num
     * @return
     */
    public boolean isValid(int num) {
        if ((num > 0 && num % 2 == 0) || num <= 0) {
            return true;
        }
        return false;
    }

    public boolean isValidReversed(int num) {
        if (num > 0 && num % 2 != 0) {
            return false;
        }
        return true;
    }
}
