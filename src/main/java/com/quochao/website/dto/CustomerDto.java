package com.quochao.website.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CustomerDto implements Serializable {
    private String name;
    private String email;
    private String address;
    private String phone;
    private Integer quantity;
}
