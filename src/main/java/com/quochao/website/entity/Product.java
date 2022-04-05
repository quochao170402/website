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
@ToString
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

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<Image> images;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<ProductColor> productColors;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<ProductSize> productSizes;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<Review> reviews;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<OrderDetail> orderDetails;

    @JsonIgnore
    @Transient
    private MultipartFile file;
}
