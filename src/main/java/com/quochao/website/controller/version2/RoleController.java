package com.quochao.website.controller.version2;

import com.quochao.website.common.ResponseHelper;
import com.quochao.website.entity.Role;
import com.quochao.website.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/roles")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @GetMapping
    public ResponseEntity<Object> findAll() {
        return ResponseHelper.getResponse(roleService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> saveRole(@RequestBody Role role) {
        Role saved = roleService.saveRole(role);

        if (saved.isEmpty()) {
            return ResponseHelper.getErrorResponse("Role already existed", HttpStatus.BAD_REQUEST);
        }
        return ResponseHelper.getResponse(saved, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Object> updateRole(@RequestBody Role role) {
        Role updated = roleService.update(role);

        if (updated.isEmpty()) {
            return ResponseHelper.getErrorResponse("Role not found", HttpStatus.BAD_REQUEST);
        }
        return ResponseHelper.getResponse(updated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> disableRole(@PathVariable long id) {
        Role deleted = roleService.delete(id);

        if (deleted.isEmpty()) {
            return ResponseHelper.getErrorResponse("Role not found", HttpStatus.BAD_REQUEST);
        }
        return ResponseHelper.getResponse(deleted, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> enableRole(@PathVariable long id) {
        Role enabled = roleService.enableRole(id);

        if (enabled.isEmpty()) {
            return ResponseHelper.getErrorResponse("Role not found", HttpStatus.BAD_REQUEST);
        }
        return ResponseHelper.getResponse(enabled, HttpStatus.OK);
    }
}
