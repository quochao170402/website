package com.quochao.website.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Set;

@Data
public class ProductImagesDto implements Serializable {
    private Long productId;
    private Set<MultipartFile> files;
}
