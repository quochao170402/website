package com.quochao.website.service.impl;

import com.quochao.website.entity.Size;
import com.quochao.website.repository.SizeRepository;
import com.quochao.website.service.SizeService;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Optional;

@Service
@Data
@Transactional
public class SizeServiceImpl implements SizeService {
    private final SizeRepository sizeRepository;

    @Override
    public Page<Size> findAll(Integer page, Integer size, String field, String dir) {
        return dir.equals("asc") ?
                sizeRepository.findAll(PageRequest.of(page, size, Sort.by(field).ascending())) :
                sizeRepository.findAll(PageRequest.of(page, size, Sort.by(field).descending()));
    }

    @Override
    public Size save(Size size) {
        if (sizeRepository.getSizeByName(size.getName()) != null) throw new IllegalStateException("Size was existed");
        size.setCode(size.getName().trim().toLowerCase().replaceAll(" ", "-"));
        size.setState(true);
        size.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        return sizeRepository.save(size);
    }

    @Override
    public Size update(Size size) {
        Optional<Size> optionalSize = sizeRepository.findById(size.getId());
        if (!optionalSize.isPresent()) throw new IllegalStateException("Not found size");
        Size existed = sizeRepository.getSizeByName(size.getName());
        Size updated = optionalSize.get();
        if (existed != null && !existed.getName().equals(updated.getName()))
            throw new IllegalStateException("Size was existed");

        updated.setName(size.getName());
        updated.setCode(updated.getName().trim().toLowerCase().replaceAll(" ", "-"));
        updated.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return updated;
    }

    @Override
    public Size delete(Long id) {
        Optional<Size> optionalSize = sizeRepository.findById(id);
        if (!optionalSize.isPresent()) throw new IllegalStateException("Not found size");
        Size size = optionalSize.get();
        size.setState(false);
        size.setDeletedAt(new Timestamp(System.currentTimeMillis()));
        return size;
    }

    @Override
    public Size getById(Long id) {
        Optional<Size> optionalSize = sizeRepository.findById(id);
        if (optionalSize.isPresent()) return optionalSize.get();
        else throw new IllegalStateException("Not found size");
    }

    @Override
    public Size enableSize(Long id) {
        Optional<Size> optionalSize = sizeRepository.findById(id);
        if (!optionalSize.isPresent()) throw new IllegalStateException("Not found size");
        Size size = optionalSize.get();
        size.setState(true);
        size.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return size;
    }
}
