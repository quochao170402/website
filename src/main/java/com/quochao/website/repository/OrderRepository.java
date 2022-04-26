package com.quochao.website.repository;

import com.quochao.website.entity.Order;
import com.quochao.website.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findAllByUser(User user);

    void deleteByUserAndId(User user, Long id);
}
