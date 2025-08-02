package com.maestria.springmvcstock.service;
import java.util.List;

import com.maestria.springmvcstock.model.Order;
public interface OrderService {
    List<Order> getAllOrders();
    Order getOrderById(Long id);
    Order createOrder(Order order);
    Order updateOrder(Long id, Order order);
    void deleteOrder(Long id);
}