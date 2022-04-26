package com.quochao.website.controller;

import com.quochao.website.dto.CustomerDto;
import com.quochao.website.security.MyUserDetail;
import com.quochao.website.service.OrderService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@Data
@CrossOrigin("*")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<?> carts() {
        return ResponseEntity.ok(orderService.getCart());
    }

    @PostMapping
    public ResponseEntity<?> addToCart(
            @RequestParam(name = "code") String code,
            @RequestParam(name = "quantity", defaultValue = "1") Integer quantity) {
        return ResponseEntity.ok(orderService.addToCart(code, quantity));
    }

    @PutMapping
    public ResponseEntity<?> updateQuantity(
            @RequestParam(name = "code") String code,
            @RequestParam(name = "quantity") Integer quantity) {
        return ResponseEntity.ok(orderService.updateQuantity(code, quantity));
    }

    @DeleteMapping
    public ResponseEntity<?> removeItem(@RequestParam(name = "code") String code) {
        return ResponseEntity.ok(orderService.removeItem(code));
    }

    @PostMapping("/checkouts")
    public ResponseEntity<?> checkout(@RequestBody CustomerDto customerDto, @AuthenticationPrincipal MyUserDetail userDetails) {
        return ResponseEntity.ok(orderService.checkout(customerDto,userDetails.getUser()));
    }

    @GetMapping("/histories")
    public ResponseEntity<?> getOrderHistory(@AuthenticationPrincipal MyUserDetail userDetails) {
        return ResponseEntity.ok(orderService.getOrderHistory(userDetails.getUser()));
    }

    @DeleteMapping("/histories/{id}")
    public ResponseEntity<?> removeOrderHistory(@AuthenticationPrincipal MyUserDetail userDetails,@PathVariable Long id) {
        return ResponseEntity.ok(orderService.removeOrderHistory(userDetails.getUser(),id));
    }
}
