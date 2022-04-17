package com.quochao.website.service.impl;

import com.quochao.website.entity.Color;
import com.quochao.website.repository.ColorRepository;
import com.quochao.website.service.ColorService;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
@Data
public class ColorServiceImpl implements ColorService {
    private final ColorRepository colorRepository;

    @Override
    public Color update(Color color) {
        Color updated = colorRepository.getById(color.getId());
        updated.setName(color.getName());
        updated.setCode(updated.getName().trim().toLowerCase().replaceAll(" ", "-"));
        updated.setState(color.getState());
        updated.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return color;
    }

    @Override
    public Color save(Color color) {
        if (colorRepository.getByName(color.getName()) != null) throw new IllegalStateException("Color was existed");
        color.setCode(color.getName().trim().toLowerCase().replaceAll(" ", "-"));
        color.setState(true);
        color.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        return colorRepository.save(color);
    }

    @Override
    public List<Color> findAll() {
        return colorRepository.findAll();
    }
}
