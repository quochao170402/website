package com.quochao.website.controller.admin;

import com.quochao.website.service.FeedBackService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/api/v1/feedbacks")
@Data
@CrossOrigin("*")
public class AdminFeedBackController {
    private final FeedBackService feedBackService;

    @GetMapping
    public ResponseEntity<?> findAll(@RequestParam(name = "page", required = false, defaultValue = "0") Integer page) {
        return ResponseEntity.ok(feedBackService.findAll(page));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(feedBackService.findById(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok(feedBackService.delete(id));
    }
}
