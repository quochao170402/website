package com.quochao.website.controller;

import com.quochao.website.dto.LoginDto;
import com.quochao.website.dto.ResetPasswordDto;
import com.quochao.website.entity.User;
import com.quochao.website.security.MyUserDetail;
import com.quochao.website.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Data
@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        MyUserDetail userDetail = (MyUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new ResponseEntity<>(userDetail.getUser(), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@ModelAttribute User user) {
        return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
    }

    @PostMapping("/reset")
    public ResponseEntity<?> resetPassword(@AuthenticationPrincipal MyUserDetail userDetails, @RequestBody ResetPasswordDto dto) {
        if (userDetails == null)
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        else {
            User user = userDetails.getUser();
            if (!dto.getPassword().equals(dto.getConfirmPassword()))
                return new ResponseEntity<>("Password does not match confirm-password", HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(userService.resetPassword(user, dto.getPassword()), HttpStatus.OK);
        }
    }
}
