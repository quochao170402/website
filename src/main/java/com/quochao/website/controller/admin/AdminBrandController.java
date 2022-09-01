package com.quochao.website.controller.admin;

import com.quochao.website.entity.Brand;
import com.quochao.website.service.BrandService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/api/v1/brands")
@Data
@CrossOrigin("*")
public class AdminBrandController {
    private final BrandService brandService;

    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
            @RequestParam(name = "field", required = false, defaultValue = "id") String field,
            @RequestParam(name = "dir", required = false, defaultValue = "asc") String dir) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(brandService.findAll(page, size, field, dir));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(brandService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> addBrand(@ModelAttribute Brand brand) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(brandService.save(brand));
    }

    @PutMapping
    public ResponseEntity<?> updateBrand(@ModelAttribute Brand brand) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(brandService.update(brand));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteBrand(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(brandService.delete(id));
    }

    @PostMapping("{id}")
    public ResponseEntity<?> enableBrand(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(brandService.enableBrand(id));
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> removeBrand(@PathVariable Long id){
        return ResponseEntity.ok(brandService.removeBrand(id));
    }

}
