package com.quochao.website.service;

import com.quochao.website.entity.Brand;

public interface BrandService {
    Brand findByCode(String code);

    Brand getBrandByName(String name);
}
