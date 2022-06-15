package com.quochao.website.service;

import com.quochao.website.entity.Brand;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BrandService {
    Brand findByCode(String code);

    Brand getBrandByName(String name);

    Page<Brand> findAll(Integer page, Integer size, String field, String dir);

    Brand save(Brand brand);

    Brand update(Brand brand);

    Brand delete(Long id);

    Brand findById(Long id);

    Brand enableBrand(Long id);

    Boolean removeBrand(Long id);
}
