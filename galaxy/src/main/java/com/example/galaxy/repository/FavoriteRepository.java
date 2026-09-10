package com.example.galaxy.repository;

import com.example.galaxy.entity.FavoritePhoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<FavoritePhoto, Long> {
}
