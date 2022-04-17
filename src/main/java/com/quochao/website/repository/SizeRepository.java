package com.quochao.website.repository;

import com.quochao.website.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SizeRepository extends JpaRepository<Size,Long> {
    Size getSizeByName(String name);
}
