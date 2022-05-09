package com.quochao.website.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CustomerDto implements Serializable {
    private String name;
    private String email;
    private String address;
    private String phone;
    List<CartItemsDto> cart;
}
