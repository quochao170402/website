package com.quochao.website.service;

import com.quochao.website.entity.Review;
import com.quochao.website.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReviewService {

    Review save(User user, String code, Review review);

    Review update(User user, Review review);

    Boolean delete(User user, Long id);

    Page<Review> findAll(Integer page, Integer size, String field, String dir);

    Review getById(Long id);

    Boolean delete(Long id);

    List<Review> getReviewsByUser(User user);

    Boolean removeReview(User user, Long id);
}
