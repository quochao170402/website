package com.quochao.website.controller.admin;


import com.quochao.website.dto.CreateProductDto;
import com.quochao.website.dto.ProductImagesDto;
import com.quochao.website.service.ProductService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("admin/api/v1/products")
@Data
@CrossOrigin("*")
public class AdminProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<?> findAll(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
            @RequestParam(name = "field", required = false, defaultValue = "id") String field,
            @RequestParam(name = "dir", required = false, defaultValue = "asc") String dir) {
        return ResponseEntity.ok(productService.findAllProducts(page, size, field, dir));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> addProduct(@ModelAttribute CreateProductDto createProductDto) {
        return ResponseEntity.ok(productService.save(createProductDto));
    }

    @PutMapping
    public ResponseEntity<?> updateProduct(@ModelAttribute CreateProductDto createProductDto) {
        return ResponseEntity.ok(productService.updateProduct(createProductDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.deleteProduct(id));
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> enableProduct(@PathVariable Long id) {
        return ResponseEntity.ok(productService.enableProduct(id));
    }

    @PostMapping("/images")
    public ResponseEntity<?> addProductImages(@ModelAttribute ProductImagesDto productImagesDto) {
        return ResponseEntity.ok(productService.saveImages(productImagesDto));
    }

    @PutMapping("/images/{id}")
    public ResponseEntity<?> updateProductImages(
            @PathVariable Long id,
            @ModelAttribute MultipartFile imageUrl) {
        return ResponseEntity.ok(productService.updateImages(id, imageUrl));
    }

    @DeleteMapping("/images/{id}")
    public ResponseEntity<?> deleteProductImages(@PathVariable Long id) {
        return ResponseEntity.ok(productService.deleteImages(id));
    }
}
