package com.quochao.website.controller.admin;

import com.quochao.website.entity.Size;
import com.quochao.website.service.SizeService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Data
@RestController
@RequestMapping("admin/api/v1/sizes")
@CrossOrigin("*")
public class AdminSizeController {
    private final SizeService sizeService;

    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
            @RequestParam(name = "field", required = false, defaultValue = "name") String field,
            @RequestParam(name = "dir", required = false, defaultValue = "asc") String dir) {
        return ResponseEntity.ok(sizeService.findAll(page, size, field, dir));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getSizeById(@PathVariable Long id) {
        return ResponseEntity.ok(sizeService.getById(id));
    }

    @PostMapping
    public ResponseEntity<?> addSize(@RequestBody Size size) {
        return ResponseEntity.ok(sizeService.save(size));
    }

    @PutMapping
    public ResponseEntity<?> updateSize(@RequestBody Size size) {
        return ResponseEntity.ok(sizeService.update(size));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteSize(@PathVariable Long id) {
        return ResponseEntity.ok(sizeService.delete(id));
    }

    @PostMapping("{id}")
    public ResponseEntity<?> enableSize(@PathVariable Long id) {
        return ResponseEntity.ok(sizeService.enableSize(id));
    }

    @DeleteMapping("remove/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        return ResponseEntity.ok(sizeService.remove(id));
    }

}
