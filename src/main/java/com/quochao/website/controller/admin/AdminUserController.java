package com.quochao.website.controller.admin;

import com.quochao.website.entity.User;
import com.quochao.website.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/api/v1/users")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AdminUserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<?> findAll(
            @RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(name = "size", required = false, defaultValue = "10") Integer size,
            @RequestParam(name = "field", required = false, defaultValue = "id") String field,
            @RequestParam(name = "dir", required = false, defaultValue = "asc") String dir) {
        return ResponseEntity.ok(userService.findAll(page,size,field,dir));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody User user) {
        return ResponseEntity.ok(userService.save(user));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody User user) {
        return ResponseEntity.ok(userService.update(user));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> changeRole(@PathVariable Long id) {
        return ResponseEntity.ok(userService.changeRole(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return ResponseEntity.ok(userService.delete(id));
    }

    @PostMapping("{id}")
    public ResponseEntity<?> enableUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.enableUser(id));
    }
}
