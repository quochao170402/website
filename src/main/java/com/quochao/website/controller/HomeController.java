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
    private final BrandRepository brandRepository;

    @GetMapping
    public String home(){
        Brand brand = new Brand();
        Random random = new Random();
        brand.setName("name"+random.nextInt(100000));
        brand.setCode("code"+random.nextInt(100000));
        brand.setLogo("logo"+random.nextDouble());
        return "Home Page " + brandRepository.save(brand);
    }
}
