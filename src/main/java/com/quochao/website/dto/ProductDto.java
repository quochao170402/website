package com.quochao.website.dto;

import com.quochao.website.entity.Brand;
import com.quochao.website.entity.Category;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ProductDto implements Serializable {
    private Long id;
    private String name;
    private String code;
    private Double price;
    private Double competitive;
    private String shortDescription;
    private String description;
    private String image;
    private Boolean state;
    private String brandName;
    private String categoryName;
    private List<String> colors;
    private List<String> sizes;
}
