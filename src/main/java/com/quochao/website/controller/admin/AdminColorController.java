package com.quochao.website.controller.admin;


import com.quochao.website.entity.Color;
import com.quochao.website.service.ColorService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Data
@RestController
@RequestMapping("api/v1/admin/colors")
@CrossOrigin("*")
public class AdminColorController {
    private final ColorService colorService;

    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
            @RequestParam(name = "field", required = false, defaultValue = "name") String field,
            @RequestParam(name = "dir", required = false, defaultValue = "asc") String dir) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(colorService.findAll(page, size, field, dir));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> updateColor(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(colorService.getById(id));
    }

    @PostMapping
    public ResponseEntity<?> add(@ModelAttribute Color color){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(colorService.save(color));
    }

    @PutMapping
    public ResponseEntity<?> update(@ModelAttribute Color color){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(colorService.update(color));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(colorService.delete(id));
    }
}
