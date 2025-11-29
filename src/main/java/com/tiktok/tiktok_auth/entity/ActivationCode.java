package com.tiktok.tiktok_auth.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "activation_codes")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActivationCode extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String code;

    @Column(nullable = false)
    private LocalDateTime expiredAt;

    @Column(nullable = false)
    @Builder.Default
    private Boolean used = false;

    private LocalDateTime usedAt;
}
