package com.quochao.website.service;

import com.quochao.website.entity.Product;
import com.quochao.website.entity.ProductSize;
import com.quochao.website.entity.Size;

import java.util.List;

public interface ProductSizeService {
    ProductSize add(Product product, Size size);

    List<ProductSize> addAll(Product product, List<Size> sizes);
}
