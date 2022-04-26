package com.quochao.website.service.impl;

import com.quochao.website.entity.Role;
import com.quochao.website.repository.RoleRepository;
import com.quochao.website.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class RoleServiceImpl implements RoleService {
    private final RoleRepository repository;

    @Override
    public List<Role> findAll() {
        return repository.findAll();
    }

    @Override
    public Role getRoleById(Long id) {
        Optional<Role> role = repository.findById(id);
        return role.orElseThrow(() -> new IllegalStateException("Not found role"));
    }

    @Override
    public Role save(Role role) {
        if (repository.findByName(role.getName()) != null) throw new IllegalStateException("Role was existed");
        role.setCode(role.getName().trim().toLowerCase().replaceAll(" ", "-"));
        role.setState(true);
        role.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        return repository.save(role);
    }

    @Override
    public Role update(Role role) {
        Optional<Role> optional = repository.findById(role.getId());
        if (!optional.isPresent()) throw new IllegalStateException("Not found role");
        if (repository.findByName(role.getName()) != null) throw new IllegalStateException("Role was existed");
        Role updated = optional.get();
        updated.setName(role.getName());
        updated.setCode(updated.getName().trim().toLowerCase().replaceAll(" ", "-"));
        updated.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return updated;
    }

    @Override
    public Role delete(Long id) {
        Optional<Role> optional = repository.findById(id);
        if (!optional.isPresent()) throw new IllegalStateException("Not found role");
        Role role = optional.get();
        role.setState(false);
        role.setDeletedAt(new Timestamp(System.currentTimeMillis()));
        return role;
    }

    @Override
    public Role enableRole(Long id) {
        Optional<Role> optional = repository.findById(id);
        if (!optional.isPresent()) throw new IllegalStateException("Not found role");
        Role role = optional.get();
        role.setState(true);
        role.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return role;
    }
}
