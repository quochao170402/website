package com.quochao.website.service.impl;

import com.quochao.website.entity.Category;
import com.quochao.website.repository.CategoryRepository;
import com.quochao.website.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Locale;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.getCategoryByName(name);
    }

    @Override
    public Category save(Category category) {
        if (getCategoryByName(category.getName()) != null) throw new IllegalStateException("Category was existed.");
        category.setCode(category.getName().trim().toLowerCase().replaceAll(" ", "-"));
        category.setState(true);
        category.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        return categoryRepository.save(category);
    }
}
