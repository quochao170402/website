package com.quochao.website.service;

import com.quochao.website.dto.CreateProductDto;
import com.quochao.website.dto.ProductDetailDto;
import com.quochao.website.dto.ProductDto;
import com.quochao.website.dto.ProductImagesDto;
import com.quochao.website.entity.Image;
import com.quochao.website.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface ProductService {
    List<ProductDto> findAll();

    ProductDto findByCode(String code);

    Page<ProductDto> findAll(Integer page, Integer size, String s, String field);

    List<ProductDto> searchByKeyword(String keyword);

    Page<ProductDto> findAllByCategory(String categoryCode, Integer page, Integer size, String field, String dir);

    Page<ProductDto> findAllByBrand(String code, Integer page, Integer size, String field, String dir);

    Page<ProductDto> findAllByColor(String code, Integer page, Integer size, String field, String dir);

    Product save(CreateProductDto createProductDto);

    Product updateProduct(CreateProductDto createProductDto);

    Product deleteProduct(Long id);

    Page<ProductDto> filter(String brandCode, String categoryCode, String productSize, String productColor, Double minPrice, Double maxPrice, Integer page, Integer size, String field, String dir);

    List<ProductDto> filter(String brand, Map<String, String> map);

    ProductDetailDto findById(Long id);

    Page<Product> findAllProducts(Integer page, Integer size, String field, String dir);

    List<Image> saveImages(ProductImagesDto productImagesDto);

    Image updateImages(Long id, MultipartFile productImagesDto);

    Boolean deleteImages(Long id);
}
