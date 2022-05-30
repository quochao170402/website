package com.quochao.website.repository;

import com.quochao.website.entity.Product;
import com.quochao.website.entity.Review;
import com.quochao.website.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByUser(User user);

    List<Review> getAllByProduct(Product product);
}
