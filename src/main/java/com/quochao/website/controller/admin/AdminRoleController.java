package com.quochao.website.controller.admin;

import com.quochao.website.entity.Role;
import com.quochao.website.service.RoleService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return ResponseEntity.ok(roleService.save(role));
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
