package com.quochao.website.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {
    private Long id;
    private String name;
    private String email;
    private String address;
    private String phone;
    private String image;
    private String username;
    private String password;
}
