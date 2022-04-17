package com.quochao.website.service.impl;

import com.quochao.website.entity.Size;
import com.quochao.website.repository.SizeRepository;
import com.quochao.website.service.SizeService;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
@Data
@Transactional
public class SizeServiceImpl implements SizeService {
    private final SizeRepository sizeRepository;

    @Override
    public List<Size> findAll() {
        return sizeRepository.findAll();
    }

    @Override
    public Size save(Size size) {
        if (sizeRepository.getSizeByName(size.getName())!=null) throw new IllegalStateException("Size was existed");
        size.setCode(size.getName().trim().toLowerCase().replaceAll(" ","-"));
        size.setState(true);
        size.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        return sizeRepository.save(size);
    }

    @Override
    public Size update(Size size) {
        Size updated = sizeRepository.getById(size.getId());
        updated.setName(size.getName());
        updated.setCode(updated.getName().trim().toLowerCase().replaceAll(" ","-"));
        updated.setState(size.getState());
        updated.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return updated;
    }
}
