package com.quochao.website.controller;

import com.quochao.website.service.ProductService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/products")
@CrossOrigin("*")
@Data
public class ProductController {

    private final ProductService productService;

    //    Return all product in db. Can not return null.
    @GetMapping
    public ResponseEntity<?> getAllProduct(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
            @RequestParam(name = "field", required = false, defaultValue = "name") String field,
            @RequestParam(name = "dir", required = false, defaultValue = "asc") String dir) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.findAllByState(page, size, field, dir,true));
    }

    //    Return all product active in db to render in home-page or shop-page. Can not return null.
    @GetMapping("/true")
    public ResponseEntity<?> getAll(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
            @RequestParam(name = "field", required = false, defaultValue = "name") String field,
            @RequestParam(name = "dir", required = false, defaultValue = "asc") String dir) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.findAllByState(page, size, field, dir, true));
    }

    @GetMapping("/latest")
    public ResponseEntity<?> getLatestProducts() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.findLatestProducts());
    }

    @GetMapping("/hot")
    public ResponseEntity<?> getHotProducts() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.findHotProducts());
    }

    @GetMapping("/fields")
    public ResponseEntity<?> getAllFields() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.getAllFields());
    }

    //    Return product detail. using method in product detail page
    @GetMapping("detail/{code}")
    public ResponseEntity<?> getProduct(@PathVariable String code) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.findByCode(code));
    }

    //    Find all products by category code. Return products or throw not found exception
    @GetMapping(path = "/categories/{code}")
    public ResponseEntity<?> getAllProductByCategory(
            @PathVariable String code,
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
            @RequestParam(name = "field", required = false, defaultValue = "name") String field,
            @RequestParam(name = "dir", required = false, defaultValue = "asc") String dir) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.findAllByCategory(code, page, size, field, dir));
    }

    //    Find all products by brand code. Return products or throw not found exception
    @GetMapping(path = "/brands/{code}")
    public ResponseEntity<?> getAllProductByBrand(
            @PathVariable String code,
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
            @RequestParam(name = "field", required = false, defaultValue = "name") String field,
            @RequestParam(name = "dir", required = false, defaultValue = "asc") String dir) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.findAllByBrand(code, page, size, field, dir));
    }

    //    filter by color -> products {product -> product-colors -> color
    //    color -> product-color -> product
    @GetMapping(path = "/color/{code}")
    public ResponseEntity<?> getAllByColor(
            @PathVariable String code,
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
            @RequestParam(name = "field", required = false, defaultValue = "name") String field,
            @RequestParam(name = "dir", required = false, defaultValue = "asc") String dir) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.findAllByColor(code, page, size, field, dir));
    }

    //    similar for get products by size and tag and ...


    //    Frontend must validate keyword before request arrive server.
    //    If keyword null or empty request will redirect to home-page.
    //    So the method cannot return null.
    @GetMapping(path = "/search/{keyword}")
    public ResponseEntity<?> search(@PathVariable String keyword) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.searchByKeyword(keyword));
    }

    @GetMapping(path = "/filter")
    public ResponseEntity<?> filterMultipleField(
            @RequestParam(name = "brand-code", required = false, defaultValue = "") String brand,
            @RequestParam(name = "category-code", required = false, defaultValue = "") String category,
            @RequestParam(name = "product-size", required = false, defaultValue = "") String productSize,
            @RequestParam(name = "product-color", required = false, defaultValue = "") String productColor,
            @RequestParam(name = "min", required = false, defaultValue = "0") Double minPrice,
            @RequestParam(name = "max", required = false, defaultValue = "") Double maxPrice,
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
            @RequestParam(name = "field", required = false, defaultValue = "name") String field,
            @RequestParam(name = "dir", required = false, defaultValue = "asc") String dir) {
        return ResponseEntity.ok(productService.filter(brand, category, productSize, productColor, minPrice, maxPrice, page, size, field, dir));
    }


}
