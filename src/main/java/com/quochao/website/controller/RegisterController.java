package com.quochao.website.controller;

import com.quochao.website.entity.User;
import com.quochao.website.service.UserService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/register")
@CrossOrigin("*")
@Data
public class RegisterController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> register(@ModelAttribute User user){
        return ResponseEntity.ok(userService.save(user));
    }
}
