package com.quochao.website.service;

import com.quochao.website.entity.Size;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SizeService {
    Page<Size> findAll(Integer page, Integer size, String field, String dir);

    Size save(Size size);

    Size update(Size size);

    Size delete(Long id);

    Size getById(Long id);

    Size enableSize(Long id);

    Boolean remove(Long id);
}
