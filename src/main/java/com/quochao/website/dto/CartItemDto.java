package com.quochao.website.dto;

import com.quochao.website.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
public class CartItemDto implements Serializable {
    private String productCode;
    private Product product;
    private Integer quantity;
    private Double unitPrice;
    private Double totalPrice;
}
