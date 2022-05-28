package com.quochao.website.dto;

import lombok.Data;

import java.io.Serializable;


@Data
public class ReviewDto implements Serializable {
    private Long userId;
    private Long reviewId;
    private String code;
    private String content;
}
