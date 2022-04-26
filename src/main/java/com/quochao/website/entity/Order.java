package com.quochao.website.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @Column(name = "order_at", nullable = false)
    private Timestamp orderAt = new Timestamp(System.currentTimeMillis());

    @Column(name = "total_amount", nullable = false)
    private Integer totalAmount;

    @Column(name = "total_price", nullable = false)
    private Double totalPrice;

    // Customer's information cannot be null
    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String address;

    @Column(name = "phone_number", nullable = false)
    private String phone;

    // State in [processing,processed]
    // After checkout order, the customer's order will have a state of processing
    // When the order is deleted, its state has been processed
    @Column(nullable = false)
    private Boolean state = false;

    // After the customer's order is deleted, the information fields will be updated
    // If delete orders, deletedAt cannot be null
    @Column(name = "deleted_at")
    private Timestamp deletedAt;

    // Employee must reply to customer reason of deleted their orders
    // If delete orders, deletedReason cannot be null
    @Column(name = "deleted_reason")
    private String deletedReason;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;
}
