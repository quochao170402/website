package com.quochao.website.service.impl;

import com.cloudinary.Cloudinary;
import com.quochao.website.entity.Brand;
import com.quochao.website.repository.BrandRepository;
import com.quochao.website.service.BrandService;
import com.quochao.website.util.FileStorage;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@Data
@Transactional
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
    public Page<Brand> findAll(Integer page, Integer size, String field, String dir) {
        return (dir.equalsIgnoreCase("asc")) ?
                brandRepository.findAll(PageRequest.of(page, size, Sort.by(field).ascending())) :
                brandRepository.findAll(PageRequest.of(page, size, Sort.by(field).descending()));
    }

    @Override
    public Brand save(Brand brand) {
        if (brandRepository.getBrandByName(brand.getName()) != null)
            throw new IllegalStateException("Brand was existed");
        brand.setCode(brand.getName().toLowerCase().trim().replaceAll(" ", "-"));
        if (brand.getLogo() == null) brand.setLogo("no-image");
        brand.setState(true);
        brand.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        return brandRepository.save(brand);
    }

    @Override
    public Brand update(Brand brand) {
        if (brand == null || brand.getId() == null) throw new IllegalStateException("NULL");

        Optional<Brand> optional = brandRepository.findById(brand.getId());
        if (!optional.isPresent()) throw new IllegalStateException("Not found Brand");
        Brand updated = optional.get();
        Brand existed = brandRepository.getBrandByName(brand.getName());
        if (existed != null && !existed.getName().equals(updated.getName()))
            throw new IllegalStateException("Brand was existed");

        if (brand.getLogo() != null) updated.setLogo(brand.getLogo());
        updated.setName(brand.getName());
        updated.setCode(updated.getName().toLowerCase().trim().replaceAll(" ", "-"));
        updated.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return updated;
    }

    @Override
    public Brand delete(Long id) {
        Optional<Brand> optional = brandRepository.findById(id);
        if (!optional.isPresent()) throw new IllegalStateException("Not found brand");
        Brand brand = optional.get();
        brand.setState(false);
        brand.setDeletedAt(new Timestamp(System.currentTimeMillis()));
        return brand;
    }

    @Override
    public Brand enableBrand(Long id) {
        Optional<Brand> optional = brandRepository.findById(id);
        if (!optional.isPresent()) throw new IllegalStateException("Not found brand");
        Brand brand = optional.get();
        if (brand.getState()) return brand;
        brand.setState(true);
        brand.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return brand;
    }

    @Override
    public Brand findById(Long id) {
        Optional<Brand> optionalBrand = brandRepository.findById(id);
        if (optionalBrand.isPresent()) return optionalBrand.get();
        else throw new IllegalStateException("Not found brand");
    }
}
