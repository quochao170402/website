package com.quochao.website.controller.version2;

import com.quochao.website.common.ResponseHelper;
import com.quochao.website.entity.Brand;
import com.quochao.website.service.BrandService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/brands")
@AllArgsConstructor
public class BrandController {
    private final BrandService brandService;

    @GetMapping
    public ResponseEntity<Object> getAll(){
        return ResponseEntity.ok(ResponseHelper.getResponse(brandService.findAll(), HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<Object> saveBrand(@ModelAttribute Brand brand){
        return ResponseEntity.ok(ResponseHelper.getResponse(brandService.save(brand), HttpStatus.OK));
    }
}
