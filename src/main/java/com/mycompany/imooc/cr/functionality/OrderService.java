package com.mycompany.imooc.cr.functionality;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderService {

    private final ProductService productService;

    private final DeliveryService deliveryService;

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
            if (inventory < orderItem.getCount()) {
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
