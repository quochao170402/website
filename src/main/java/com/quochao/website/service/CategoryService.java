package com.quochao.website.service;

import com.quochao.website.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    Category getCategoryByName(String name);
}
