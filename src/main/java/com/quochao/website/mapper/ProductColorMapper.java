package com.quochao.website.mapper;

import com.quochao.website.dto.ColorDto;
import com.quochao.website.entity.ProductColor;

public class ProductColorMapper {
    private static ProductColorMapper INSTANCE;

    public static ProductColorMapper getINSTANCE() {
        if (INSTANCE == null) INSTANCE = new ProductColorMapper();
        return INSTANCE;
    }

    public ColorDto convertToDto(ProductColor entity) {
        ColorDto dto = new ColorDto();
        dto.setColorId(entity.getColor().getId());
        dto.setColorName(entity.getColor().getName());
        dto.setColorCode(entity.getColor().getCode());
        return dto;
    }

}
