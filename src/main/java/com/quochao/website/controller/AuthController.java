package com.quochao.website.controller;

import lombok.Data;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Data
@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/auth")
public class AuthController {
//    @Autowired
//    private AuthenticationManager authenticationManager;
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @PostMapping("/signin")
//    public ResponseEntity<?> authenticateUser(@RequestBody LoginDto loginDto) {
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//                loginDto.getUsername(), loginDto.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        MyUserDetail userDetail = (MyUserDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return new ResponseEntity<>(userDetail.getUser(), HttpStatus.OK);
//    }
//
//    @PostMapping("/signup")
//    public ResponseEntity<?> registerUser(@RequestBody User user) {
//        return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
//    }
////    @PostMapping("/signup")
////    public ResponseEntity<?> registerUser(@ModelAttribute User user) {
////        return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
////    }
//    @PostMapping("/reset")
//    public ResponseEntity<?> resetPassword(@RequestBody Long userId, @RequestBody ResetPasswordDto dto) {
//        User user = userService.getById(userId);
//        if (user == null)
//            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
//        else {
//            if (!dto.getPassword().equals(dto.getConfirmPassword()))
//                return new ResponseEntity<>("Password does not match confirm-password", HttpStatus.BAD_REQUEST);
//            return new ResponseEntity<>(userService.resetPassword(user, dto.getPassword()), HttpStatus.OK);
//        }
//    }
}
