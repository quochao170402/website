package com.quochao.website.service.impl;

import com.quochao.website.entity.Product;
import com.quochao.website.entity.ProductSize;
import com.quochao.website.entity.Size;
import com.quochao.website.repository.ProductSizeRepository;
import com.quochao.website.service.ProductSizeService;
import lombok.Data;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Data
@Transactional
public class ProductSizeServiceImpl implements ProductSizeService {
    private final ProductSizeRepository repository;

    @Override
    public ProductSize add(Product product, Size size) {
        ProductSize productSize = new ProductSize();
        productSize.setProduct(product);
        productSize.setSize(size);
        return repository.save(productSize);
    }

    @Override
    public List<ProductSize> addAll(Product product, Set<Size> sizes) {
        if (sizes == null) return null;
        List<ProductSize> productSizes = new ArrayList<>();
        sizes.forEach(size -> {
            ProductSize productSize = new ProductSize();
            productSize.setProduct(product);
            productSize.setSize(size);
            productSizes.add(productSize);
        });
        return repository.saveAll(productSizes);
    }

    @Override
    public List<ProductSize> updateAll(Product product, Set<Size> sizes) {
        if (sizes == null) return null;
        repository.deleteByProduct(product);
        return addAll(product, sizes);
    }


}
