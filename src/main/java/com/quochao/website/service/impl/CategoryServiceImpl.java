package com.quochao.website.service.impl;

import com.quochao.website.entity.Category;
import com.quochao.website.repository.CategoryRepository;
import com.quochao.website.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

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

    @Override
    public Category update(Category category) {
        if (category==null||category.getId()==null) throw new IllegalStateException("NULL");
        Category updated = categoryRepository.getById(category.getId());
        updated.setName(category.getName());
        updated.setCode(category.getName().trim().toLowerCase().replaceAll(" ", "-"));
        updated.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return updated;
    }

    @Override
    public Category delete(Long id) {
        if (id==null) throw new IllegalStateException("ID is null");
        Category category = categoryRepository.getById(id);
        category.setState(false);
        category.setDeletedAt(new Timestamp(System.currentTimeMillis()));
        return category;
    }
}
