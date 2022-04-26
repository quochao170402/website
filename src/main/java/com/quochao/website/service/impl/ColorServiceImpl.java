package com.quochao.website.service.impl;

import com.quochao.website.entity.Color;
import com.quochao.website.repository.ColorRepository;
import com.quochao.website.service.ColorService;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
@Data
@Transactional
public class ColorServiceImpl implements ColorService {
    private final ColorRepository colorRepository;

    @Override
    public Color update(Color color) {
        Optional<Color> optionalColor = colorRepository.findById(color.getId());
        if (!optionalColor.isPresent()) throw new IllegalStateException("Not found color");
        Color updated = optionalColor.get();
        Color existed = colorRepository.getByName(color.getName());
        if (existed != null && !updated.getName().equals(existed.getName()))
            throw new IllegalStateException("Color was existed");
        updated.setName(color.getName());
        updated.setCode(updated.getName().trim().toLowerCase().replaceAll(" ", "-"));
        updated.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return updated;
    }

    @Override
    public Color getById(Long id) {
        Optional<Color> color = colorRepository.findById(id);
        if (color.isPresent()) return color.get();
        else throw new IllegalStateException("Not found color");
    }

    @Override
    public Color delete(Long id) {
        Optional<Color> optionalColor = colorRepository.findById(id);
        if (!optionalColor.isPresent()) throw new IllegalStateException("Not found color");
        Color color = optionalColor.get();
        color.setState(false);
        color.setDeletedAt(new Timestamp(System.currentTimeMillis()));
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
    public Page<Color> findAll(Integer page, Integer size, String field, String dir) {
        return (dir.equalsIgnoreCase("asc")) ?
                colorRepository.findAll(PageRequest.of(page, size, Sort.by(field).ascending())) :
                colorRepository.findAll(PageRequest.of(page, size, Sort.by(field).descending()));
    }

    @Override
    public Color enableColor(Long id) {
        Optional<Color> optional = colorRepository.findById(id);
        if (!optional.isPresent()) throw new IllegalStateException("Not found color");
        Color color = optional.get();
        color.setState(true);
        color.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return color;
    }
}
