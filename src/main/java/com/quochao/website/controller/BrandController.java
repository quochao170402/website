package com.quochao.website.controller;


import com.quochao.website.entity.Brand;
import com.quochao.website.service.BrandService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/brands")
@Data
public class BrandController {
    private final BrandService brandService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(brandService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> add(@ModelAttribute Brand brand){
        return ResponseEntity.status(HttpStatus.OK)
                .body(brandService.save(brand));
    }

    @PutMapping
    public ResponseEntity<?> update(@ModelAttribute Brand brand){
        return ResponseEntity.status(HttpStatus.OK)
                .body(brandService.update(brand));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(brandService.delete(id));
    }

}
