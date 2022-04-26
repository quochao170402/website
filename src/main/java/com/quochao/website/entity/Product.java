package com.quochao.website.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false)
    private Double price;

    // Competitive price, it will be crossed out when displayed in browser
    @Column(nullable = false)
    private Double competitive;

    @Column(nullable = false)
    private String image;

    // Short description is a title of product, show it in product detail page or product card
    @Column(name = "short_description")
    private String shortDescription;

    private String description;

    //    State in [active,inactive]
    @Column(nullable = false)
    private Boolean state;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @Column(name = "deleted_at")
    private Timestamp deletedAt;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @OneToMany(mappedBy = "product")
    private List<Image> images;

    @OneToMany(mappedBy = "product")
    private List<ProductColor> productColors;

    @OneToMany(mappedBy = "product")
    private List<ProductSize> productSizes;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<Review> reviews;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<OrderDetail> orderDetails;
}
