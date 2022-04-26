package com.quochao.website.service.impl;

import com.quochao.website.entity.Category;
import com.quochao.website.repository.CategoryRepository;
import com.quochao.website.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Page<Category> findAll(Integer page, Integer size, String field, String dir) {
        return (dir.equalsIgnoreCase("asc")) ?
                categoryRepository.findAll(PageRequest.of(page, size, Sort.by(field).ascending())) :
                categoryRepository.findAll(PageRequest.of(page, size, Sort.by(field).descending()));
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.getCategoryByName(name);
    }

    @Override
    public Category save(Category category) {
        if (categoryRepository.getCategoryByName(category.getName()) != null)
            throw new IllegalStateException("Category was existed.");
        category.setCode(category.getName().trim().toLowerCase().replaceAll(" ", "-"));
        category.setState(true);
        category.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Category category) {
        if (category == null || category.getId() == null) throw new IllegalStateException("NULL");
        Optional<Category> optionalCategory = categoryRepository.findById(category.getId());
        Category existed = categoryRepository.getCategoryByName(category.getName());
        if (!optionalCategory.isPresent()) throw new IllegalStateException("Not found category");
        Category updated = optionalCategory.get();
        if (existed != null && !updated.getName().equals(existed.getName()))
            throw new IllegalStateException("Category was existed.");
        updated.setName(category.getName());
        updated.setCode(category.getName().trim().toLowerCase().replaceAll(" ", "-"));
        updated.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return updated;
    }

    @Override
    public Category delete(Long id) {
        Optional<Category> optional = categoryRepository.findById(id);
        if (!optional.isPresent()) throw new IllegalStateException("Not found category");
        Category category = optional.get();
        category.setState(false);
        category.setDeletedAt(new Timestamp(System.currentTimeMillis()));
        return category;
    }

    @Override
    public Category enableCategory(Long id) {
        Optional<Category> optional = categoryRepository.findById(id);
        if (!optional.isPresent()) throw new IllegalStateException("Not found category");
        Category category = optional.get();
        if (category.getState()) return category;
        category.setState(true);
        category.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return category;
    }

    @Override
    public Category getById(Long id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) return optionalCategory.get();
        else throw new IllegalStateException("Not found category");
    }
}
