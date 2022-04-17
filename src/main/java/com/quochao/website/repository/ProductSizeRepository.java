package com.quochao.website.repository;

import com.quochao.website.entity.Product;
import com.quochao.website.entity.ProductSize;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductSizeRepository extends JpaRepository<ProductSize,Long> {
    void deleteByProduct(Product product);
}
