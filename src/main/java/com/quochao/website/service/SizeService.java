package com.quochao.website.service;

import com.quochao.website.entity.Size;

import java.util.List;

public interface SizeService {
    List<Size> findAll();

    Size save(Size size);

    Size update(Size size);
}
