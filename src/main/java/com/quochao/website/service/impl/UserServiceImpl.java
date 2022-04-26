package com.quochao.website.service.impl;

import com.cloudinary.Cloudinary;
import com.quochao.website.entity.User;
import com.quochao.website.repository.UserRepository;
import com.quochao.website.security.MyUserDetail;
import com.quochao.website.service.RoleService;
import com.quochao.website.service.UserService;
import com.quochao.website.util.FileStorage;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleService roleService;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("Could not find user");
        }
        if (!user.get().getState())
            throw new IllegalStateException("Account has been deactivated. Please use another account");
        return new MyUserDetail(user.get());
    }

    @Override
    public Page<User> findAll(Integer page, Integer size, String field, String dir) {
        return dir.equalsIgnoreCase("desc") ?
                userRepository.findAll(PageRequest.of(page, size, Sort.by(field).descending())) :
                userRepository.findAll(PageRequest.of(page, size, Sort.by(field).ascending()));
    }

    @Override
    public User getById(Long id) {
        Optional<User> optional = userRepository.findById(id);
        return optional.orElseThrow(() -> new IllegalStateException("Not found user"));
    }

    @Override
    public User getByUsername(String username) {
        Optional<User> optional = userRepository.findByUsername(username);
        return optional.orElseThrow(() -> new IllegalStateException("Not found user"));
    }

    @Override
    public User save(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent())
            throw new IllegalStateException("Username already exist");
        if (!user.getPassword().equals(user.getConfirmPassword()))
            throw new IllegalStateException("Password doesn't match");

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setState(true);
        user.setRole(roleService.getRoleById(2L));
        if (user.getImage() == null) user.setImage("no-image");
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        Optional<User> optional = userRepository.findById(user.getId());
        if (!optional.isPresent()) throw new IllegalStateException("Not found user");
        User updated = optional.get();
        updated.setName(user.getName());
        updated.setEmail(updated.getEmail());
        updated.setAddress(updated.getAddress());
        updated.setPhone(updated.getPhone());
        if (user.getImage() != null) updated.setImage("no-image");
        updated.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return updated;
    }

    @Override
    public User delete(Long id) {
        Optional<User> optional = userRepository.findById(id);
        if (!optional.isPresent()) throw new IllegalStateException("Not found user");
        User user = optional.get();
        user.setDeletedAt(new Timestamp(System.currentTimeMillis()));
        user.setState(false);
        return user;
    }

    @Override
    public User changeRole(Long id) {
        Optional<User> optional = userRepository.findById(id);
        if (!optional.isPresent()) throw new IllegalStateException("Not found user");
        User user = optional.get();
        user.setRole(roleService.getRoleById(3L));
        user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return user;
    }

    @Override
    public User updateInfo(User user) {
        Optional<User> optional = userRepository.findByUsername(user.getUsername());
        if (!optional.isPresent()) throw new IllegalStateException("Invalid information");
        User updated = optional.get();
        updated.setName(user.getName());
        updated.setEmail(user.getEmail());
        updated.setAddress(user.getAddress());
        updated.setPhone(user.getPhone());
        updated.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return updated;
    }

    @Override
    public User enableUser(Long id) {
        Optional<User> optional = userRepository.findById(id);
        if (!optional.isPresent()) throw new IllegalStateException("Not found user");
        User user = optional.get();
        user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        user.setState(true);
        return user;
    }
}
