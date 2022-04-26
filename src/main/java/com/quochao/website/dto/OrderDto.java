package com.quochao.website.dto;

import com.quochao.website.entity.OrderDetail;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Data
public class OrderDto implements Serializable {
    private String customerName;
    private String email;
    private String address;
    private String phone;
    private Integer quantity;
    private Double price;
    private Timestamp orderAt;
    private Boolean state;
    List<CartItemDto> items;
}
