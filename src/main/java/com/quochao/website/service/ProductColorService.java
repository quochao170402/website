package com.quochao.website.service;

import com.quochao.website.entity.Color;
import com.quochao.website.entity.Product;
import com.quochao.website.entity.ProductColor;

import java.util.List;
import java.util.Set;

public interface ProductColorService {
    ProductColor add(Product product, Color color);
    List<ProductColor> addAll(Product product, Set<Color> colors);
    List<ProductColor> updateAll(Product product, Set<Color> colors);
}
