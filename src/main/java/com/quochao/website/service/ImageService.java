package com.quochao.website.service;

import com.quochao.website.entity.Image;

public interface ImageService {
    Image save(Image image);

    Boolean delete(Long id);

    Image findById(Long id);
}
