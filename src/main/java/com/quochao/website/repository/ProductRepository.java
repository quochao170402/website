package com.quochao.website.repository;

import com.quochao.website.dto.ProductDto;
import com.quochao.website.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p " +
            "WHERE concat(p.code,p.brand.code,p.category.code) like %?1%")
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

    Optional<Product> findProductByCode(String code);

    Optional<Product> findByCodeAndState(String code, Boolean state);

    Page<Product> findAllByState(Pageable pageable, boolean state);

//    @Query("select p.name " +
//            "from Product as p join Brand as b on p.brand.id = b.id")
//    List<Product> filter(@Param("brandCode") String brandCode, String category, String productSize, String productColor, Double minPrice, Double maxPrice);

//    @Query("select p " +
//            "from Product as p " +
//            "join Brand as b on p.brand = b " +
//            "join Category as c on p.category = c " +
//            "join ProductSize as ps on ps.product = p " +
//            "join Size as s on ps.size = s " +
//            "join ProductColor as pc on pc.product = p " +
//            "join  Color as co on pc.color = co " +
//            "where (b.code = :brandCode) " +
//            "and (c.code = :category) " +
//            "and (s.code = :productSize) " +
//            "and (co.code = :productColor) " +
//            "and (p.price> :min) " +
//            "and (p.price< :max) ")

    @Query("select DISTINCT p " +
            "from Product as p " +
            "join Brand as b on p.brand = b " +
            "join Category as c on p.category = c " +
            "join ProductSize as ps on ps.product = p " +
            "join Size as s on ps.size = s " +
            "join ProductColor as pc on pc.product = p " +
            "join  Color as co on pc.color = co " +
            "where (b.code like %:brand%) " +
            "and (c.code like %:category%) " +
            "and (s.code like %:size%) " +
            "and (co.code like %:color%) " +
            "and (p.price> :min) " +
            "and (p.price< :max) ")
    Page<Product> filter(@Param("brand") String brandCode, @Param("category") String category,
                         @Param("size") String productSize, @Param("color") String productColor,
                         @Param("min") Double min, @Param("max") Double max, Pageable pageable);
}
