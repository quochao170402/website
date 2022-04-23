package com.quochao.website.mapper;


import com.quochao.website.dto.CreateProductDto;
import com.quochao.website.dto.ProductDetailDto;
import com.quochao.website.dto.ProductDto;
import com.quochao.website.entity.Product;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.sql.Timestamp;
import java.util.stream.Collectors;

@Data
public class ProductMapper {
    private static ProductMapper INSTANCE;

    public static ProductMapper getINSTANCE() {
        if (INSTANCE == null) INSTANCE = new ProductMapper();
        return INSTANCE;
    }

    public Product convertToEntity(ProductDto dto) {
        Product entity = new Product();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setCode(dto.getCode());
        entity.setPrice(dto.getPrice());
        entity.setCompetitive(dto.getCompetitive());
        entity.setState(dto.getState());
        return entity;
    }

    public ProductDto convertToDto(Product entity) {
        ProductDto dto = new ProductDto();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCode(entity.getCode());
        dto.setPrice(entity.getPrice());
        dto.setCompetitive(entity.getCompetitive());
        dto.setShortDescription(entity.getShortDescription());
        dto.setDescription(entity.getDescription());
        dto.setState(entity.getState());
        dto.setImage(entity.getImage());

        dto.setBrandName(entity.getBrand().getName());
        dto.setCategoryName(entity.getCategory().getName());

        if (entity.getProductColors() != null)
            dto.setColors(entity.getProductColors().stream()
                    .map(e -> e.getColor().getName())
                    .collect(Collectors.toList()));
        if (entity.getProductSizes() != null)
            dto.setSizes(entity.getProductSizes().stream()
                    .map(e -> e.getSize().getName())
                    .collect(Collectors.toList()));
        return dto;
    }

    public Page<ProductDto> convertPageEntityToDto(Page<Product> page) {
        ProductMapper mapper = new ProductMapper();
        return page.map(mapper::convertToDto);
    }

    public Product createProductToEntity(CreateProductDto dto) {
        Product product = new Product();
        if (dto.getId() != null) product.setId(dto.getId());
        product.setName(dto.getName());
        product.setCode(dto.getName().trim().toLowerCase().replaceAll(" ", "-"));
        product.setPrice(dto.getPrice());
        product.setCompetitive(dto.getCompetitive());
        product.setShortDescription(dto.getShortDescription());
        product.setDescription(dto.getDescription());
        product.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        product.setState(true);
        product.setBrand(dto.getBrand());
        product.setCategory(dto.getCategory());
        product.setFile(dto.getFile());
        return product;
    }

    public ProductDetailDto convertToProductDetailDto(Product product) {
        ProductDetailDto dto = new ProductDetailDto();
        dto.setProduct(product);
        dto.setImages(product.getImages());
        return dto;
    }

}
