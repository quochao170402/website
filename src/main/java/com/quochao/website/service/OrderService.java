package com.quochao.website.service;

import com.quochao.website.dto.CartDto;
import com.quochao.website.dto.CartItemDto;
import com.quochao.website.dto.CustomerDto;
import com.quochao.website.entity.Order;
import com.quochao.website.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderService {
    CartDto getCart();

    CartItemDto addToCart(String productId, Integer quantity);

    CartItemDto updateQuantity(String productId, Integer quantity);

    CartDto removeItem(String code);

    Order checkout(CustomerDto customerDto);

    List<Order> getOrderHistory(Long userId);

    Boolean removeOrderHistory(User user, Long id);

    Page<Order> getAll(Integer page, Integer size);

    Boolean checkOrder(Long id);

    Boolean deleteOrder(Long id, String reason);

    Order getOrderDetail(Long id);

    int removeAllOrderDetail();
}
