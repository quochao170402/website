package com.quochao.website.controller.version2;

import com.quochao.website.common.ResponseHelper;
import com.quochao.website.dto.UserDto;
import com.quochao.website.entity.User;
import com.quochao.website.mapper.UserMapper;
import com.quochao.website.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v2/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<Object> findAll() {
        List<UserDto> users = (userService.findAll().stream()
                .map(UserMapper.INSTANCE::toDto)
                .collect(Collectors.toList()));

        return ResponseHelper.getResponse(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findDetail(@PathVariable Long id) {
        User user = userService.getById(id);
        if (user.isEmpty()) {
            return ResponseHelper.getErrorResponse(
                    "Not found user with id " + id, HttpStatus.BAD_REQUEST);
        }

        return ResponseHelper.getResponse(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> saveUser(@ModelAttribute UserDto dto, @PathVariable MultipartFile file) {
        if (dto == null) {
            return ResponseHelper.getErrorResponse(
                    "User is null", HttpStatus.BAD_REQUEST);
        }

        User newUser = UserMapper.INSTANCE.toEntity(dto);
        newUser.setFile(file);
        User saved = userService.save(newUser);
        if (saved.isEmpty()) {
            return ResponseHelper.getErrorResponse(
                    "User already existed", HttpStatus.BAD_REQUEST);
        }

        return ResponseHelper.getResponse(
                UserMapper.INSTANCE.toDto(saved), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Object> updateUser(@RequestBody UserDto dto) {
        if (dto == null) {
            return ResponseHelper.getErrorResponse(
                    "User is null", HttpStatus.BAD_REQUEST);
        }
        User user = UserMapper.INSTANCE.toEntity(dto);

        User updated = userService.update(user);
        if (updated.isEmpty()) {
            return ResponseHelper.getErrorResponse(
                    "Not found user", HttpStatus.BAD_REQUEST);
        }

        return ResponseHelper.getResponse(
                UserMapper.INSTANCE.toDto(updated), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> disableUser(@PathVariable Long id) {
        User disabled = userService.delete(id);
        if (disabled.isEmpty()) {
            return ResponseHelper.getErrorResponse(
                    "Not found user with id " + id, HttpStatus.BAD_REQUEST);
        }

        return ResponseHelper.getResponse(disabled, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> enableUser(@PathVariable Long id) {
        User enabled = userService.enableUser(id);
        if (enabled.isEmpty()) {
            return ResponseHelper.getErrorResponse(
                    "Not found user with id " + id, HttpStatus.BAD_REQUEST);
        }

        return ResponseHelper.getResponse(enabled, HttpStatus.OK);
    }

}
