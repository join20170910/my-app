package com.mycompany.imooc.cr.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderService {

    private final ProductService productService;

    private final DeliveryService deliveryService;

    /**
     * 提交订单，流程如下：
     * <ol>
     * <li>将订单提交请求转换为订单条目</li>
     * <li>检测库存是否充足，以可以购买。如果库存不足，抛出异常{@link InventoryNotEnoughException}</li>
     * <li>更新产品库存</li>
     * <li>生成订单</li>
     * </ol>
     *
     * @param submitOrderRequest 订单提交信息
     */
    public void submit(SubmitOrderRequest submitOrderRequest) {
        List<Order.OrderItem> orderItems = mapToOrderItems(submitOrderRequest);
        validateInventoryAvailable(orderItems);
        updateProductInventory(orderItems);
        generateOrder(submitOrderRequest, orderItems);
    }

    private List<Order.OrderItem> mapToOrderItems(SubmitOrderRequest submitOrderRequest) {
        return submitOrderRequest.getProducts()
                .stream()
                .map(productItem -> new Order.OrderItem(productItem.getProductId(), productItem.getCount()))
                .collect(Collectors.toList());
    }

    private void validateInventoryAvailable(List<Order.OrderItem> orderItems) {
        orderItems.forEach(orderItem -> {
            int inventory = productService.getProductInventory(orderItem.getProductId());
            if (inventory - orderItem.getCount() >= -5) {
                throw new InventoryNotEnoughException(
                        "product " + orderItem.getProductId() + " inventory not enough");
            }
        });
    }

    private void updateProductInventory(List<Order.OrderItem> orderItems) {
        orderItems.forEach(orderItem -> {
            int inventory = productService.getProductInventory(orderItem.getProductId());
            productService.updateProductInventory(orderItem.getProductId(), inventory - orderItem.getCount());
        });
    }

    private void generateOrder(SubmitOrderRequest submitOrderRequest, List<Order.OrderItem> orderItems) {
        DeliveryInfo deliveryInfo = deliveryService.getDeliveryInfo(submitOrderRequest.getDeliveryInfoId());
        Order order = new Order(orderItems, deliveryInfo.getPhone(), deliveryInfo.getAddress(),
                deliveryInfo.getConcatName());
        saveInDB(order);
    }

    private void saveInDB(Order order) {
        //save in db
    }

}
