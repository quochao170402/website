package com.quochao.website.service.impl;

import com.cloudinary.Cloudinary;
import com.quochao.website.entity.User;
import com.quochao.website.repository.UserRepository;
import com.quochao.website.service.FileUploaderService;
import com.quochao.website.service.RoleService;
import com.quochao.website.service.UserService;
import com.quochao.website.util.FileUploaderConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleService roleService;
    @Autowired
    private FileUploaderService fileUploaderService;
    @Autowired
    private Cloudinary cloudinary;

//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> user = userRepository.findByUsername(username);
//        if (user.isEmpty()) {
//            throw new UsernameNotFoundException("Could not find user");
//        }
//        if (!user.get().getState())
//            throw new IllegalStateException("Account has been deactivated. Please use another account");
//        return new MyUserDetail(user.get());
//    }


    @Override
    public List<User> findAll() {
        log.info("Get all user");
        return userRepository.findAll();
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
        return optional.orElse(User.EMPTY_USER);
    }

    @Override
    public User getByUsername(String username) {
        Optional<User> optional = userRepository.findByUsername(username);
        return optional.orElse(User.EMPTY_USER);
    }

    @Override
    public User save(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent())
            return User.EMPTY_USER;

        if (user.getFile() != null) {
            String filename = fileUploaderService.saveFile(user.getFile(),
                    FileUploaderConstant.DIR_UPLOAD_USER_IMAGE, user.getUsername());

            if (filename != null) {
                user.setImage(filename);

            } else {
                user.setImage("no-image");
            }

        }

        user.setPassword(user.getPassword());
        user.setState(true);
        user.setRole(roleService.getRoleById(2L));
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        return userRepository.save(user);
    }

    @Override
    public User saveUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            log.error("username already existed");
            return User.EMPTY_USER;
        }

        user.setState(true);
        user.setRole(roleService.findByName("ROLE_USER"));
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        log.info("Saved new user {}", user.getName());
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        Optional<User> optional = userRepository.findById(user.getId());
        if (optional.isEmpty()) {
            log.error("Not found user {}", user.getId());
            return User.EMPTY_USER;
        }

        User updated = optional.get();
        updated.setName(user.getName());
        updated.setEmail(user.getEmail());
        updated.setAddress(user.getAddress());
        updated.setPhone(user.getPhone());
//        if (user.getFile() != null) {
//            FileStorage fileStorage = new FileStorage(cloudinary, "user");
//            updated.setImage(fileStorage.saveFile(user.getFile(), updated.getUsername()));
//        }
        updated.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        log.info("Modified user from {} to {}", user.getName(), updated.getName());
        return updated;
    }

    @Override
    public User delete(Long id) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isEmpty()) {
            log.error("Not found user {}", id);
            return User.EMPTY_USER;
        }
        User user = optional.get();
        user.setDeletedAt(new Timestamp(System.currentTimeMillis()));
        user.setState(false);

        log.info("Deleted user {}", user.getName());
        return user;
    }

    @Override
    public User changeRole(Long id) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isEmpty()) {
            log.error("Not found user {}", id);
            return User.EMPTY_USER;
        }
        User user = optional.get();
        user.setRole(roleService.getRoleById(3L));
        user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        log.info("Change role for user {}", user.getName());
        return user;
    }

    @Override
    public User updateInfo(User user) {
        Optional<User> optional = userRepository.findByUsername(user.getUsername());
        if (optional.isEmpty()) {
            log.error("Not found user {}", user.getId());
            return User.EMPTY_USER;
        }
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
        if (optional.isEmpty()) {
            log.error("Not found user {}", id);
            return User.EMPTY_USER;
        }
        User user = optional.get();
        user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        user.setDeletedAt(null);
        user.setState(true);

        log.info("Enabled user {}", user.getName());
        return user;
    }

    @Override
    public User resetPassword(User user, String newPassword) {
        Optional<User> old = userRepository.findByUsername(user.getUsername());
        old.ifPresent(value -> value.setPassword(newPassword));
        return user;
    }
}
