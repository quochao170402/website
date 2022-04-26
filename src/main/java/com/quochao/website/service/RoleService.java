package com.quochao.website.service;

import com.quochao.website.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll();

    Role getRoleById(Long id);

    Role save(Role role);

    Role update(Role role);

    Role delete(Long id);

    Role enableRole(Long id);
}
