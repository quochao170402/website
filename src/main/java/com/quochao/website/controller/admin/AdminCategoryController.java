package com.quochao.website.controller.admin;

import com.quochao.website.entity.Category;
import com.quochao.website.service.CategoryService;
import lombok.Data;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Data
@RestController
@RequestMapping("admin/api/v1/categories")
@CrossOrigin("*")
public class AdminCategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
            @RequestParam(name = "field", required = false, defaultValue = "id") String field,
            @RequestParam(name = "dir", required = false, defaultValue = "asc") String dir) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryService.findAll(page, size, field, dir));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
            return ResponseEntity.status(HttpStatus.OK).body(categoryService.getById(id));
    }

    @PostMapping
    public ResponseEntity<?> addCategory(@RequestBody Category category) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryService.save(category));
    }

    @PutMapping
    public ResponseEntity<?> updateCategory(@RequestBody Category category) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryService.update(category));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryService.delete(id));
    }

    @PostMapping("{id}")
    public ResponseEntity<?> enableCategory(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(categoryService.enableCategory(id));
    }
}
