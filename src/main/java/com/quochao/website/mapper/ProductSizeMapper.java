package com.quochao.website.mapper;

import com.quochao.website.dto.SizeDto;
import com.quochao.website.entity.ProductSize;

public class ProductSizeMapper {
    private static ProductSizeMapper INSTANCE;

    public static ProductSizeMapper getINSTANCE() {
        if (INSTANCE == null) INSTANCE = new ProductSizeMapper();
        return INSTANCE;
    }

    public SizeDto covertToDto(ProductSize entity) {
        SizeDto dto = new SizeDto();
        dto.setSizeId(entity.getSize().getId());
        dto.setSizeName(entity.getSize().getName());
        dto.setSizeCode(entity.getSize().getCode());
        return dto;
    }
}
