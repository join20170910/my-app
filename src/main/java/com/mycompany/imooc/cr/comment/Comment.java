package com.mycompany.imooc.cr.comment;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 注释示例
 *
 * @author gavincook
 * @since 1.0
 */
public class Comment {

    @Autowired
    private OrderRepository orderRepository;

    public void deleteOrder(String orderId) {
        orderRepository.delete(orderId);
    }



    private static final int MAXIMUM_POWER_OF_2 = 1 << 30;

    /**
     * 获取大于或等于指定数字的2的次方的最小值，如果计算出来的数字大于{@link #MAXIMUM_POWER_OF_2}，
     * 则以{@link #MAXIMUM_POWER_OF_2}为准
     * 比如：
     * <pre>
     * getMinPowerOf2GreaterOrEqual(7) = 8
     * getMinPowerOf2GreaterOrEqual(16) = 16
     * </pre>
     * 2的次方，在二进制中，只会有一位数字为1，其余全部为0。2的次方减1，右侧所有数字都为1，其他为0.
     * 因此，针对获取大于或等于指定数字的2的次方的最小值，只需要：
     * <ol>
     * <li>将指定数字减1</li>
     * <li>将减1后的数字，从最左侧的1开始，所有右边的数字都变为1
     * （由于整型为32位，所以我们只需要处理五次位操作即可，1，2，4，8，16）</li>
     * <li>将结果加1即可</li>
     * </ol>
     *
     * @param number 用于获取2的次方的数字
     * @return 大于或等于指定数字的2的次方的最小值
     */
    public int getMinPowerOf2GreaterOrEqual(int number) {
        int n = number - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return n > MAXIMUM_POWER_OF_2 ? MAXIMUM_POWER_OF_2 : n + 1;
    }
}
