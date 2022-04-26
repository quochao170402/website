package com.quochao.website.controller;

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

    @PostMapping("{code}")
    public ResponseEntity<?> reviewProduct(@AuthenticationPrincipal MyUserDetail userDetails,
                                           @PathVariable String code,
                                           @RequestBody Review review) {
        return ResponseEntity.ok(reviewService.save(userDetails.getUser(), code, review));
    }

    @PutMapping
    public ResponseEntity<?> updateReview(@AuthenticationPrincipal MyUserDetail userDetails,
                                          @RequestBody Review review) {
        return ResponseEntity.ok(reviewService.update(userDetails.getUser(), review));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteReview(@AuthenticationPrincipal MyUserDetail userDetails,
                                          @PathVariable Long id) {
        return ResponseEntity.ok(reviewService.delete(userDetails.getUser(), id));
    }
}
