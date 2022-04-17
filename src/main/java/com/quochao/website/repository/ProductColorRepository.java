package com.quochao.website.repository;

import com.quochao.website.entity.Product;
import com.quochao.website.entity.ProductColor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductColorRepository extends JpaRepository<ProductColor, Long> {

    void deleteByProduct(Product product);

    void deleteProductColorByProduct(Product product);
}
