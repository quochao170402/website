package com.quochao.website.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SizeDto implements Serializable {
    private Long sizeId;
    private String sizeName;
    private String sizeCode;
}
