package com.quochao.website.controller;

import com.quochao.website.service.CategoryService;
import com.quochao.website.service.ProductService;
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


}
