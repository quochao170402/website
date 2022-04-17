package com.quochao.website.service;

import com.quochao.website.entity.Category;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    Category getCategoryByName(String name);

    Category save(Category category);
}
