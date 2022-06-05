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

    @GetMapping("/{userId}")
    public ResponseEntity<?> account(@PathVariable Long userId) {
        User user = userService.getById(userId);
        return ResponseEntity.ok(user);
    }

    @PutMapping
    public ResponseEntity<?> updateInfo(@RequestBody User user) {
        return ResponseEntity.ok(userService.updateInfo(user));
    }

    @GetMapping("{userId}/histories")
    public ResponseEntity<?> getOrders(@PathVariable Long userId) {
        User user = userService.getById(userId);
        return ResponseEntity.ok(orderService.getOrderHistory(userId));
    }

    @DeleteMapping("/histories/{id}")
    public ResponseEntity<?> removeOrder(@RequestBody Long userId, @PathVariable Long id) {
        User user = userService.getById(userId);
        return ResponseEntity.ok(orderService.removeOrderHistory(user, id));
    }

    @GetMapping("{userId}/reviews")
    public ResponseEntity<?> getReviews(@PathVariable Long userId) {
        User user   = userService.getById(userId);
        return ResponseEntity.ok(reviewService.getReviewsByUser(user));
    }

    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<?> removeReview(@RequestBody Long userId,
                                          @PathVariable Long id) {
        User user = userService.getById(userId);
        return ResponseEntity.ok(reviewService.removeReview(user,id));
    }

}
