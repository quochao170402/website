package com.quochao.website.dto;

import com.quochao.website.entity.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
public class CreateProductDto implements Serializable {

    private Long id;
    private String name;
    private Double price;
    private Double competitive;
    private String shortDescription;
    private String description;
    private Brand brand;
    private Category category;
    private String image;
    private MultipartFile file;
    private Set<Color> colors;
    private Set<Size> sizes;
}
