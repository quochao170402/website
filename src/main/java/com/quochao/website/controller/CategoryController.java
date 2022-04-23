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
    public ResponseEntity<?> getAll(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
            @RequestParam(name = "field", required = false, defaultValue = "name") String field,
            @RequestParam(name = "dir", required = false, defaultValue = "asc") String dir) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryService.findAll(page, size, field, dir));
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
