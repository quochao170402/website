package com.quochao.website.controller;

import com.quochao.website.service.ProductService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Data
@RestController
@RequestMapping(path = "/api/v1/home")
@CrossOrigin("*")
public class HomeController {

    private final ProductService productService;
    @GetMapping
    public ResponseEntity<?> home() {

        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.findAll());
    }
}
