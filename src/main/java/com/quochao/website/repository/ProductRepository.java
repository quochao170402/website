package com.quochao.website.repository;

import com.quochao.website.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product as p WHERE p.name like %?1%")
    List<Product> searchProductByKeyword(String keyword);

    Page<Product> findAllByCategoryCode(String categoryCode, Pageable pageable);

    Page<Product> findAllByBrandCode(String brandCode, Pageable pageable);

    boolean existsAllByBrandCode(String branCode);

    //    Join two table -> bad
    @Query("SELECT p from Product as p join ProductColor as pc on pc.product = p join Color as c on pc.color = c where c.code = ?1")
    Page<Product> findAllByColorCode(String color, Pageable pageable);

    Optional<Product> findProductByName(String name);

    boolean existsAllByCategoryCode(String categoryCode);

    Product getByName(String name);
}
