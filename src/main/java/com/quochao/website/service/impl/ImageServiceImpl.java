package com.quochao.website.service.impl;

import com.quochao.website.entity.Image;
import com.quochao.website.repository.ImageRepository;
import com.quochao.website.service.ImageService;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Data
@Service
@Transactional
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;

    @Override
    public Image save(Image image) {
        return imageRepository.save(image);
    }

    @Override
    public Boolean delete(Long id) {
        Optional<Image> image = imageRepository.findById(id);
        if (image.isPresent()) {
            imageRepository.delete(image.get());
            return true;
        } else
            return false;
    }

    @Override
    public Image findById(Long id) {
        Optional<Image> optional = imageRepository.findById(id);
        return optional.orElse(null);
    }
}
