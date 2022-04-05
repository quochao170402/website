package com.quochao.website.repository;

import com.quochao.website.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category getCategoryByName(String name);
}
