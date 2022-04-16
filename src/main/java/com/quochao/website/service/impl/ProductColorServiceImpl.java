package com.quochao.website.service.impl;

import com.quochao.website.entity.Color;
import com.quochao.website.entity.Product;
import com.quochao.website.entity.ProductColor;
import com.quochao.website.repository.ProductColorRepository;
import com.quochao.website.service.ProductColorService;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class ProductColorServiceImpl implements ProductColorService {
    private final ProductColorRepository repository;

    @Override
    public ProductColor add(Product product, Color color) {
        ProductColor productColor = new ProductColor();
        productColor.setProduct(product);
        productColor.setColor(color);
        return repository.save(productColor);
    }

    @Override
    public List<ProductColor> addAll(Product product, List<Color> colors) {
        List<ProductColor> productColors = new ArrayList<>();
        colors.forEach(color -> {
            ProductColor productColor = new ProductColor();
            productColor.setProduct(product);
            productColor.setColor(color);
            productColors.add(productColor);
        });
        return repository.saveAll(productColors);
    }
}