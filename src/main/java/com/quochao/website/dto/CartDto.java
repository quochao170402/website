package com.quochao.website.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CartDto implements Serializable {
    private List<CartItemDto> items;
    private Integer totalAmount;
    private Double totalPrice;
}
