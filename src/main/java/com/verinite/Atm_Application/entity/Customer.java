package com.verinite.Atm_Application.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

    @Entity
    @Table(name = "customer")
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public class Customer {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "customer_id")
        private Long customerId;

        @Column(name = "customer_name", nullable = false)
        private String customerName;

        @Column(nullable = false, unique = true, length = 10)
        private String mobile;

        @Column(nullable = false, unique = true)
        private String email;

        @Column(name = "created_at")
        private LocalDateTime createdAt;

    }

