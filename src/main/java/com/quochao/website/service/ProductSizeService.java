package com.quochao.website.service;

import com.quochao.website.entity.Product;
import com.quochao.website.entity.ProductSize;
import com.quochao.website.entity.Size;

import java.util.List;
import java.util.Set;

public interface ProductSizeService {
    ProductSize add(Product product, Size size);

    List<ProductSize> addAll(Product product, Set<Size> sizes);
    List<ProductSize> updateAll(Product product, Set<Size> sizes);
}
