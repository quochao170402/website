package com.quochao.website.service;

import com.quochao.website.entity.Brand;

import java.util.List;

public interface BrandService {
    Brand findByCode(String code);

    Brand getBrandByName(String name);

    List<Brand> findAll();

    Brand save(Brand brand);

    Brand update(Brand brand);

    Brand delete(Long id);
}
