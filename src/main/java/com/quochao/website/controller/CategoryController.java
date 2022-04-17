package com.quochao.website.controller;

import com.quochao.website.entity.Category;
import com.quochao.website.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/categories")
@CrossOrigin("*")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> getAllCategories() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> add(@ModelAttribute Category category) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryService.save(category));
    }

    @PutMapping
    public ResponseEntity<?> update(@ModelAttribute Category category) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryService.update(category));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryService.delete(id));
    }


}
