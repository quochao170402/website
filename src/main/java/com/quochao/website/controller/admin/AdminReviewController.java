package com.quochao.website.controller.admin;

import com.quochao.website.service.ReviewService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/api/v1/reviews")
@CrossOrigin("*")
@Data
public class AdminReviewController {
    private final ReviewService reviewService;

    @GetMapping
    public ResponseEntity<?> findAll(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
            @RequestParam(name = "field", required = false, defaultValue = "id") String field,
            @RequestParam(name = "dir", required = false, defaultValue = "asc") String dir) {
        return ResponseEntity.ok(reviewService.findAll(page, size, field, dir));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return ResponseEntity.ok(reviewService.getById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return ResponseEntity.ok(reviewService.delete(id));
    }
}
