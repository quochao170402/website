package com.quochao.website.service;

import com.quochao.website.entity.Category;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {
    Page<Category> findAll(Integer page, Integer size, String field, String dir);

    Category getCategoryByName(String name);

    Category save(Category category);

    Category update(Category category);

    Category delete(Long id);

    Category getById(Long id);

    Category enableCategory(Long id);

    Boolean removeListCategories(Long id);

    List<Category> findAll();
}
