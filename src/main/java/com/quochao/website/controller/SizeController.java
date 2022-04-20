package com.quochao.website.controller;

import com.quochao.website.entity.Size;
import com.quochao.website.service.SizeService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Data
@RestController
@RequestMapping("api/v1/sizes")
@CrossOrigin("*")
public class SizeController {
    private final SizeService service;

    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
            @RequestParam(name = "field", required = false, defaultValue = "name") String field,
            @RequestParam(name = "dir", required = false, defaultValue = "asc") String dir) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.findAll(page, size, field, dir));
    }

    @PostMapping
    public ResponseEntity<?> add(@ModelAttribute Size size) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.save(size));
    }

    @PutMapping
    public ResponseEntity<?> update(@ModelAttribute Size size) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.update(size));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(service.delete(id));
    }
}
