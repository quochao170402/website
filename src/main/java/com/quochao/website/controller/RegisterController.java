package com.quochao.website.controller;

import com.quochao.website.entity.User;
import com.quochao.website.service.UserService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/register")
@CrossOrigin("*")
@Data
public class RegisterController {
    private final UserService userService;

    public ResponseEntity<?> register(@ModelAttribute User user){
        return ResponseEntity.ok(userService.save(user));
    }
}
