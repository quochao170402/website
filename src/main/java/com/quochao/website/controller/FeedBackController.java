package com.quochao.website.controller;

import com.quochao.website.entity.FeedBack;
import com.quochao.website.service.FeedBackService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/feedbacks")
@Data
@CrossOrigin("*")
public class FeedBackController {
    private final FeedBackService feedBackService;

    @PostMapping
    public ResponseEntity<?> sendFeedBack(@RequestBody FeedBack feedBack) {
        return ResponseEntity.ok(feedBackService.save(feedBack));
    }
}
