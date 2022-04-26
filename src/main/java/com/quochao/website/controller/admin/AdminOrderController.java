package com.quochao.website.controller.admin;

import com.quochao.website.service.OrderService;
import com.quochao.website.service.UserService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/api/v1/orders")
@CrossOrigin("*")
@Data
public class AdminOrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size){
        return ResponseEntity.ok(orderService.getAll(page,size));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getOrderDetail(@PathVariable Long id){
        return ResponseEntity.ok(orderService.getOrderDetail(id));
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> checkOrder(@PathVariable Long id){
        return ResponseEntity.ok(orderService.checkOrder(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id,@RequestBody String reason){
        return ResponseEntity.ok(orderService.deleteOrder(id,reason));
    }
}
