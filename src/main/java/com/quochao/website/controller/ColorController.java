package com.quochao.website.controller;

import com.quochao.website.entity.Color;
import com.quochao.website.service.ColorService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/colors")
@Data
public class ColorController {
    private final ColorService colorService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(colorService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> add(@ModelAttribute Color color){
        return ResponseEntity.status(HttpStatus.OK)
                .body(colorService.save(color));
    }

    @PutMapping
    public ResponseEntity<?> update(@ModelAttribute Color color){
        return ResponseEntity.status(HttpStatus.OK)
                .body(colorService.update(color));
    }
}
