package com.example.galaxy.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class FavoritePhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String url;
    private String date;
    private String memo;

    @Column(columnDefinition = "TEXT")
    private String explanation;
}