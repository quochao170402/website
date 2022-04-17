package com.quochao.website.controller;

import com.quochao.website.dto.CreateProductDto;
import com.quochao.website.dto.ProductDto;
import com.quochao.website.entity.Product;
import com.quochao.website.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/v1/products")
@CrossOrigin("*")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    //    Return all product in db to render in home-page or shop-page. Can not return null.
    @GetMapping
    public ResponseEntity<?> getAllProduct(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
            @RequestParam(name = "field", required = false, defaultValue = "name") String field,
            @RequestParam(name = "dir", required = false, defaultValue = "asc") String dir) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.findAll(page, size, field, dir));
    }

//    Return product detail. using method in product detail page
    @GetMapping("{code}")
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

    //    start crud products + getAll()
    //    Add new product, validate product information before model attribute arrive server
    @PostMapping
    public ResponseEntity<?> addProduct(@ModelAttribute CreateProductDto createProductDto) {
        System.out.println(createProductDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productService.addProduct(createProductDto));
    }

    @PutMapping
    public ResponseEntity<?> updateProduct(@ModelAttribute CreateProductDto createProductDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productService.updateProduct(createProductDto));
    }

    //    Delete product by product id
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.deleteProduct(id));
    }

//    end crud products

    @GetMapping(path = "/filter/{brandCode}")
    public ResponseEntity<?> filterMultipleField(
            @PathVariable String brandCode,
            @RequestParam(name = "category-code", required = false, defaultValue = "") String categoryCode,
            @RequestParam(name = "product-size", required = false, defaultValue = "") String productSize,
            @RequestParam(name = "product-color", required = false, defaultValue = "") String productColor,
            @RequestParam(name = "min", required = false, defaultValue = "0") Double minPrice,
            @RequestParam(name = "max", required = false, defaultValue = "") Double maxPrice,
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
            @RequestParam(name = "field", required = false, defaultValue = "name") String field,
            @RequestParam(name = "dir", required = false, defaultValue = "asc") String dir) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.filter(brandCode, categoryCode, productSize, productColor, minPrice, maxPrice, page, size, field, dir));
    }

//    @GetMapping(path = "/filter/{brand}")
//    public ResponseEntity<?> filter(
//            @PathVariable String brand,
//            @RequestParam Map<String, String> map) {
//        List<ProductDto> list = productService.filter(brand,map);
//    }

}
