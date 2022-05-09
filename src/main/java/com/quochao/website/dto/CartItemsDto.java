package com.quochao.website.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CartItemsDto implements Serializable {
    private String code;
    private Integer quantity;
}
