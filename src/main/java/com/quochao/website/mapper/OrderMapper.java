package com.quochao.website.mapper;

import com.quochao.website.dto.CartDto;
import com.quochao.website.dto.CartItemDto;
import com.quochao.website.dto.CustomerDto;
import com.quochao.website.dto.OrderDto;
import com.quochao.website.entity.Order;
import com.quochao.website.entity.OrderDetail;
import com.quochao.website.entity.Product;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class OrderMapper {
    private static OrderMapper INSTANCE;

    public static OrderMapper getINSTANCE() {
        if (INSTANCE == null) INSTANCE = new OrderMapper();
        return INSTANCE;
    }

    public Order convertToOrder(CustomerDto customerDto, CartDto cartDto){
        Order order = new Order();
        order.setOrderAt(new Timestamp(System.currentTimeMillis()));
        order.setTotalPrice(cartDto.getTotalPrice());
        order.setTotalAmount(cartDto.getTotalAmount());
        order.setAddress(customerDto.getAddress());
        order.setEmail(customerDto.getEmail());
        order.setPhone(customerDto.getPhone());
        order.setCustomerName(customerDto.getName());
        order.setState(true);
        return order;
    }

    public OrderDto convertToOrderDto(Order order, List<OrderDetail> orderDetails){
        OrderDto dto = new OrderDto();
        dto.setCustomerName(order.getCustomerName());
        dto.setEmail(order.getEmail());
        dto.setAddress(order.getAddress());
        dto.setPhone(order.getPhone());
        dto.setQuantity(order.getTotalAmount());
        dto.setPrice(order.getTotalPrice());
        dto.setState(order.getState());
        dto.setOrderAt(order.getOrderAt());
        dto.setItems(orderDetails.stream().map(this::convertToCartItemDto).collect(Collectors.toList()));
        return dto;
    }

    public OrderDetail convertToOrderDetail(CartItemDto item){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProduct(item.getProduct());
        orderDetail.setUnitPrice(item.getUnitPrice());
        orderDetail.setQuantity(item.getQuantity());
        orderDetail.setTotalPrice(item.getTotalPrice());
        return orderDetail;
    }

    public CartItemDto convertToCartItemDto(OrderDetail orderDetail){
        CartItemDto cartItemDto = new CartItemDto();
        Product product = orderDetail.getProduct();
        cartItemDto.setProduct(product);
        cartItemDto.setProductCode(product.getCode());
        cartItemDto.setQuantity(orderDetail.getQuantity());
        cartItemDto.setUnitPrice(orderDetail.getUnitPrice());
        cartItemDto.setTotalPrice(orderDetail.getTotalPrice());
        return cartItemDto;
    }
}
