package com.quochao.website.service;

import com.quochao.website.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    Page<User> findAll(Integer page, Integer size, String field, String dir);

    User getById(Long id);

    User getByUsername(String username);

    User save(User user);

    User update(User user);

    User delete(Long id);

    User changeRole(Long id);

    User updateInfo(User user);

    User enableUser(Long id);

    User resetPassword(User user, String newPassword);

    List<User> findAll();

    User saveUser(User user);

}
