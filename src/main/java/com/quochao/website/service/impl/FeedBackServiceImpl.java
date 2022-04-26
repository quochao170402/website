package com.quochao.website.service.impl;

import com.quochao.website.entity.FeedBack;
import com.quochao.website.repository.FeedBackRepository;
import com.quochao.website.service.FeedBackService;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@Data
public class FeedBackServiceImpl implements FeedBackService {
    private final FeedBackRepository feedBackRepository;

    @Override
    public FeedBack save(FeedBack feedBack) {
        return feedBackRepository.save(feedBack);
    }

    @Override
    public Page<FeedBack> findAll(Integer page) {
        return feedBackRepository.findAll(PageRequest.of(page, 10, Sort.by("createdAt").descending()));
    }

    @Override
    public FeedBack findById(Long id) {
        return feedBackRepository.findById(id).orElseThrow(() -> new IllegalStateException("Not found feedback"));
    }

    @Override
    public Boolean delete(Long id) {
        Optional<FeedBack> optional = feedBackRepository.findById(id);
        if (!optional.isPresent()) throw new IllegalStateException("Not found feedback");
        feedBackRepository.delete(optional.get());
        return true;
    }
}
