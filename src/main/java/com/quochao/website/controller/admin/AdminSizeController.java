package com.quochao.website.controller.admin;

import com.quochao.website.entity.Size;
import com.quochao.website.service.SizeService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Data
@RestController
@RequestMapping("api/v1/admin/sizes")
@CrossOrigin("*")
public class AdminSizeController {
    private final SizeService sizeService;

    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
            @RequestParam(name = "field", required = false, defaultValue = "name") String field,
            @RequestParam(name = "dir", required = false, defaultValue = "asc") String dir) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(sizeService.findAll(page, size, field, dir));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getSizeById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(sizeService.getById(id));
    }

    @PostMapping
    public ResponseEntity<?> addSize(@ModelAttribute Size size) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(sizeService.save(size));
    }

    @PutMapping
    public ResponseEntity<?> updateSize(@ModelAttribute Size size) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(sizeService.update(size));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteSize(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(sizeService.delete(id));
    }

}
