package com.quochao.website.service;

import com.quochao.website.entity.FeedBack;
import org.springframework.data.domain.Page;

public interface FeedBackService {
    FeedBack save(FeedBack feedBack);

    Page<FeedBack> findAll(Integer page);

    FeedBack findById(Long id);

    Boolean delete(Long id);
}
