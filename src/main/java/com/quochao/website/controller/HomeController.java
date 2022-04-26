package com.quochao.website.controller;

import com.quochao.website.service.ProductService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Data
@Controller
@CrossOrigin("*")
public class HomeController {

    private final ProductService productService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
