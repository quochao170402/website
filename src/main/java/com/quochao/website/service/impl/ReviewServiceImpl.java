package com.quochao.website.service.impl;

import com.quochao.website.entity.Product;
import com.quochao.website.entity.Review;
import com.quochao.website.entity.User;
import com.quochao.website.repository.ProductRepository;
import com.quochao.website.repository.ReviewRepository;
import com.quochao.website.service.ReviewService;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Data
@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;

    @Override
    public Review save(User user, String code, Review review) {
        if (user == null)
            throw new IllegalStateException("Unauthorized");
        Optional<Product> optional = productRepository.findProductByCode(code);
        if (!optional.isPresent()) throw new IllegalStateException("Not found product");
        review.setUser(user);
        review.setProduct(optional.get());
        review.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        return reviewRepository.save(review);
    }

    @Override
    public Review update(User user, Review review) {
        if (user == null) throw new IllegalStateException("Unauthorized");
        Optional<Review> optional = reviewRepository.findById(review.getId());

        if (!optional.isPresent()) throw new IllegalStateException("Not found review");
        Review updated = optional.get();

        if (!updated.getUser().equals(user)) throw new IllegalStateException("Can't edit other people's reviews");

        updated.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        updated.setContent(review.getContent());
        return review;
    }

    @Override
    public Boolean delete(User user, Long id) {
        if (user == null) throw new IllegalStateException("Unauthorized");
        Optional<Review> optional = reviewRepository.findById(id);
        if (!optional.isPresent()) throw new IllegalStateException("Not found review");
        Review review = optional.get();
        if (!review.getUser().equals(user))
            throw new IllegalStateException("Can't delete other people's reviews");
        reviewRepository.delete(review);
        return true;
    }


    @Override
    public Page<Review> findAll(Integer page, Integer size, String field, String dir) {
        return dir.equals("desc") ?
                reviewRepository.findAll(PageRequest.of(page, size, Sort.by(field).descending())) :
                reviewRepository.findAll(PageRequest.of(page, size, Sort.by(field).ascending()));

    }

    @Override
    public Review getById(Long id) {
        Optional<Review> optional = reviewRepository.findById(id);
        if (!optional.isPresent()) throw new IllegalStateException("Not found review");
        return optional.get();
    }

    @Override
    public Boolean delete(Long id) {
        Optional<Review> optional = reviewRepository.findById(id);
        if (!optional.isPresent()) throw new IllegalStateException("Not found review");
        reviewRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Review> getReviewsByUser(User user) {
        return reviewRepository.findAllByUser(user);
    }

    @Override
    public Boolean removeReview(User user, Long id) {
        Optional<Review> optional = reviewRepository.findById(id);
        if (!optional.isPresent() || !optional.get().getUser().equals(user)) throw new IllegalStateException("Not found review");
        reviewRepository.deleteById(id);
        return true;
    }
}
