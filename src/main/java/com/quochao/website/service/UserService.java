package com.quochao.website.service;

import com.quochao.website.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    Page<User> findAll(Integer page, Integer size, String field, String dir);

    User getById(Long id);

    User getByUsername(String username);

    User save(User user);

    User update(User user);

    User delete(Long id);

    User changeRole(Long id);

    User updateInfo(User user);

    User enableUser(Long id);
}
