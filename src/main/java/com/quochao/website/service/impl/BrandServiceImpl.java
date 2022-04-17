package com.quochao.website.service.impl;

import com.cloudinary.Cloudinary;
import com.quochao.website.entity.Brand;
import com.quochao.website.repository.BrandRepository;
import com.quochao.website.service.BrandService;
import com.quochao.website.util.FileStorage;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.List;

@Service
@Data
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;
    private final Cloudinary cloudinary;

    @Override
    public Brand findByCode(String code) {
        return null;
    }

    @Override
    public Brand getBrandByName(String name) {
        return brandRepository.getBrandByName(name);
    }

    @Override
    public List<Brand> findAll() {
        return brandRepository.findAll();
    }

    @Override
    public Brand save(Brand brand, MultipartFile file) {
        if (brandRepository.getBrandByName(brand.getName()) != null)
            throw new IllegalStateException("Brand was existed");
        brand.setCode(brand.getName().toLowerCase().trim().replaceAll(" ", "-"));
        if (file != null) {
            FileStorage fileStorage = new FileStorage(cloudinary, "brand");
            brand.setLogo(fileStorage.saveFile(file, brand.getCode()));
        } else {
            brand.setLogo("no-image");
        }

        brand.setState(true);
        brand.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        return brandRepository.save(brand);
    }
}
