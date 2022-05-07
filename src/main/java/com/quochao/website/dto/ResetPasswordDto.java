package com.quochao.website.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResetPasswordDto implements Serializable {
    private String password;
    private String confirmPassword;
}
