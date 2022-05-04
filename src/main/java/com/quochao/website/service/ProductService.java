package com.quochao.website.service;

import com.quochao.website.dto.CreateProductDto;
import com.quochao.website.dto.ProductDetailDto;
import com.quochao.website.dto.ProductImagesDto;
import com.quochao.website.entity.Image;
import com.quochao.website.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    Product findByCode(String code);

    Page<Product> findAll(Integer page, Integer size, String s, String field);

    List<Product> searchByKeyword(String keyword);

    Page<Product> findAllByCategory(String categoryCode, Integer page, Integer size, String field, String dir);

    Page<Product> findAllByBrand(String code, Integer page, Integer size, String field, String dir);

    Page<Product> findAllByColor(String code, Integer page, Integer size, String field, String dir);

    Product save(CreateProductDto createProductDto);

    Product updateProduct(CreateProductDto createProductDto);

    Product deleteProduct(Long id);

    Product enableProduct(Long id);

    Page<Product> filter(String brandCode, String categoryCode, String productSize, String productColor, Double minPrice, Double maxPrice, Integer page, Integer size, String field, String dir);

    ProductDetailDto findById(Long id);

    Page<Product> findAllProducts(Integer page, Integer size, String field, String dir);

    List<Image> saveImages(ProductImagesDto productImagesDto);

    Image updateImages(Long id, MultipartFile image);

    Boolean deleteImages(Long id);

    Page<Product> findAllByState(Integer page, Integer size, String field, String dir, boolean state);
}
