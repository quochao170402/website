package com.quochao.website.controller.admin;


import com.quochao.website.entity.Color;
import com.quochao.website.service.ColorService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Data
@RestController
@RequestMapping("admin/api/v1/colors")
@CrossOrigin("*")
public class AdminColorController {
    private final ColorService colorService;

    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
            @RequestParam(name = "field", required = false, defaultValue = "name") String field,
            @RequestParam(name = "dir", required = false, defaultValue = "asc") String dir) {
        return ResponseEntity.ok(colorService.findAll(page, size, field, dir));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return ResponseEntity.ok(colorService.getById(id));
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody Color color){
        return ResponseEntity.ok(colorService.save(color));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Color color){
        return ResponseEntity.ok(colorService.update(color));
    }

    @PostMapping("{id}")
    public ResponseEntity<?> enableColor(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(colorService.enableColor(id));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return ResponseEntity.ok(colorService.delete(id));
    }
}
