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
@CrossOrigin("*")
public class BrandController {
    private final BrandService brandService;

    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
            @RequestParam(name = "field", required = false, defaultValue = "name") String field,
            @RequestParam(name = "dir", required = false, defaultValue = "asc") String dir) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(brandService.findAll(page, size, field, dir));
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
