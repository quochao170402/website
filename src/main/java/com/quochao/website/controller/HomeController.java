package com.quochao.website.controller;

import com.quochao.website.entity.Brand;
import com.quochao.website.repository.BrandRepository;
import lombok.Data;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping(path = "")
@CrossOrigin("*")
@Data
public class HomeController {

    @GetMapping
    public String home() {
        return "Home Page ";
    }
}
