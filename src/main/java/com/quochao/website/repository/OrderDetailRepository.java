package com.quochao.website.repository;

import com.quochao.website.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    @Modifying
    @Query("Delete from OrderDetail")
    int removeAllOrderDetail() ;

}
