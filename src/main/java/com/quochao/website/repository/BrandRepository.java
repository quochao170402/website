package com.quochao.website.repository;

import com.quochao.website.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
    Brand getBrandByName(String name);
}
