package com.quochao.website.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ColorDto implements Serializable {
    private Long colorId;
    private String colorName;
    private String colorCode;
}
