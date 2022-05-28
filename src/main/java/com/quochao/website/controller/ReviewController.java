package com.quochao.website.controller;

import com.quochao.website.dto.ReviewDto;
import com.quochao.website.entity.Review;
import com.quochao.website.security.MyUserDetail;
import com.quochao.website.service.ReviewService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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
