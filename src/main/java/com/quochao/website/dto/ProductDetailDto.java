package com.quochao.website.dto;

import com.quochao.website.entity.Image;
import com.quochao.website.entity.Product;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ProductDetailDto implements Serializable {
    private Product product;
    private List<Image> images;
}
