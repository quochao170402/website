package com.quochao.website.service;

import com.quochao.website.entity.Color;

import java.util.List;

public interface ColorService {
    List<Color> findAll();

    Color save(Color color);

    Color update(Color color);
}
