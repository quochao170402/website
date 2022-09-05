package com.quochao.website.controller.admin;

import com.quochao.website.entity.Role;
import com.quochao.website.service.RoleService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin/api/v1/roles")
@Data
@CrossOrigin("*")
public class AdminRoleController {
    private final RoleService roleService;

    @GetMapping
    public ResponseEntity<?> findAll(){
        return ResponseEntity.ok(roleService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        return ResponseEntity.ok(roleService.getRoleById(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Role role){
        return ResponseEntity.ok(roleService.saveRole(role));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Role role){
        return ResponseEntity.ok(roleService.update(role));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return ResponseEntity.ok(roleService.delete(id));
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> enableRole(@PathVariable Long id){
        return ResponseEntity.ok(roleService.enableRole(id));
    }

}
