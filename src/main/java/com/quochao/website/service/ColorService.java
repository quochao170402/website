package com.quochao.website.service;

import com.quochao.website.entity.Color;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ColorService {
    Page<Color> findAll(Integer page, Integer size, String field, String dir);

    Color save(Color color);

    Color update(Color color);

    Color getById(Long id);

    Color delete(Long id);
}
