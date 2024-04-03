package com.quenzvezda.todolistjavaspring.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100) // Konfigurasi kolom database untuk title
    private String title;

    @Column(length = 500) // Konfigurasi kolom database untuk description
    private String description;

    @Column(nullable = false) // Konfigurasi kolom database untuk status
    private Boolean status = false; // Default value untuk status adalah false

    @Column(nullable = false, updatable = false) // Konfigurasi kolom database untuk createdAt
    private LocalDateTime createdAt;

    @Column(nullable = false) // Konfigurasi kolom database untuk updatedAt
    private LocalDateTime updatedAt;

    @PrePersist // Metode ini akan dipanggil sebelum entitas disimpan ke dalam database pertama kali
    protected void onCreate() {
        updatedAt = createdAt = LocalDateTime.now();
    }

    @PreUpdate // Metode ini akan dipanggil setiap kali entitas diperbarui
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
