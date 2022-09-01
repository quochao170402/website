package com.quochao.website.controller;

import com.quochao.website.dto.ReviewDto;
import com.quochao.website.service.ReviewService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reviews")
@CrossOrigin("*")
@Data
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<?> reviewProduct(@RequestBody ReviewDto reviewDto) {
        return ResponseEntity.ok(reviewService.save(reviewDto));
    }

    @PutMapping
    public ResponseEntity<?> updateReview(@RequestBody ReviewDto reviewDto) {
        return ResponseEntity.ok(reviewService.update(reviewDto));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteReview(@RequestBody ReviewDto reviewDto) {
        return ResponseEntity.ok(reviewService.delete(reviewDto));
    }
}
