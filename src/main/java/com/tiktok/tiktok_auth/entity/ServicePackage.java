package com.tiktok.tiktok_auth.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "service_packages")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServicePackage extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String code;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer durationDays;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal price;

    private String description;

    @Column(nullable = false)
    @Builder.Default
    private Boolean active = true;
}
