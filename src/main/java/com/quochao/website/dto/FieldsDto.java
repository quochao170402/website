package com.quochao.website.dto;

import com.quochao.website.entity.Brand;
import com.quochao.website.entity.Category;
import com.quochao.website.entity.Color;
import com.quochao.website.entity.Size;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class FieldsDto implements Serializable {
    private List<Brand> brands;
    private List<Category> categories;
    private List<Color> colors;
    private List<Size> sizes;
}
