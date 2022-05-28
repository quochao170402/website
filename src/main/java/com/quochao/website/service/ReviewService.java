package com.quochao.website.service;

import com.quochao.website.dto.ReviewDto;
import com.quochao.website.entity.Review;
import com.quochao.website.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ReviewService {

    Review save(ReviewDto reviewDto);

    Review update(ReviewDto reviewDto);

    Boolean delete(User user, Long id);

    Page<Review> findAll(Integer page, Integer size, String field, String dir);

    Review getById(Long id);

    Boolean delete(ReviewDto reviewDto);
    Boolean delete(Long id);

    List<Review> getReviewsByUser(User user);

    Boolean removeReview(User user, Long id);
}
