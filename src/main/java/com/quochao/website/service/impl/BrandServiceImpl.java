package com.quochao.website.service.impl;

import com.quochao.website.entity.Brand;
import com.quochao.website.repository.BrandRepository;
import com.quochao.website.service.BrandService;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;

    @Override
    public Brand findByCode(String code) {
        return null;
    }

    @Override
    public Brand getBrandByName(String name) {
        return brandRepository.getBrandByName(name);
    }
}
