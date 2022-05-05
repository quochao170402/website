package com.quochao.website.controller;

import com.quochao.website.entity.User;
import com.quochao.website.security.MyUserDetail;
import com.quochao.website.service.OrderService;
import com.quochao.website.service.ReviewService;
import com.quochao.website.service.UserService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/accounts")
@Data
@CrossOrigin("*")
public class AccountController {

    private final UserService userService;
    private final OrderService orderService;
    private final ReviewService reviewService;

    @GetMapping
    public ResponseEntity<?> account(@AuthenticationPrincipal MyUserDetail userDetails) {
        return ResponseEntity.ok(userDetails.getUser());
    }

    @PutMapping
    public ResponseEntity<?> updateInfo(@ModelAttribute User user) {
        return ResponseEntity.ok(userService.updateInfo(user));
    }

    @GetMapping("/histories")
    public ResponseEntity<?> getOrders(@AuthenticationPrincipal MyUserDetail userDetails) {
        return ResponseEntity.ok(orderService.getOrderHistory(userDetails.getUser()));
    }

    @DeleteMapping("/histories/{id}")
    public ResponseEntity<?> removeOrder(@AuthenticationPrincipal MyUserDetail userDetails, @PathVariable Long id) {
        return ResponseEntity.ok(orderService.removeOrderHistory(userDetails.getUser(), id));
    }

    @GetMapping("/reviews")
    public ResponseEntity<?> getReviews(@AuthenticationPrincipal MyUserDetail userDetails) {
        return ResponseEntity.ok(reviewService.getReviewsByUser(userDetails.getUser()));
    }

    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<?> removeReview(@AuthenticationPrincipal MyUserDetail userDetails, @PathVariable Long id) {
        return ResponseEntity.ok(reviewService.removeReview(userDetails.getUser(), id));
    }

}
