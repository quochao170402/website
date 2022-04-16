package com.quochao.website.dto;

import com.quochao.website.entity.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;

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
    private MultipartFile file;
    private List<Color> colors;
    private List<Size> sizes;
}
