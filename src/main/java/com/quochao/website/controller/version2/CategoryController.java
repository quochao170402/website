package com.quochao.website.controller.version2;

import com.quochao.website.common.ResponseHelper;
import com.quochao.website.entity.Category;
import com.quochao.website.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/categories")
@AllArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Object> findAll() {
        return ResponseHelper.getResponse(categoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        return ResponseHelper.getResponse(categoryService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> addCategory(@RequestBody Category category) {
        return ResponseHelper.getResponse(categoryService.save(category), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Object> updateCategory(@RequestBody Category category) {
        return ResponseHelper.getResponse(categoryService.update(category), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable Long id) {
        return ResponseHelper.getResponse(categoryService.delete(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> enableCategory(@PathVariable Long id) {
        return ResponseHelper.getResponse(categoryService.enableCategory(id), HttpStatus.OK);
    }
}
