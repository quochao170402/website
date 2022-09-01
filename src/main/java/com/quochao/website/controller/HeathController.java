package com.quochao.website.controller;

import com.quochao.website.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/heath")
@RequiredArgsConstructor
public class HeathController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Object> findAll(){
        return ResponseEntity.ok().body(productService.findAll());
    }
}
