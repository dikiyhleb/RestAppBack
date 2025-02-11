package dev.barapp.repositories;

import dev.barapp.entities.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.*;

public interface ImageRespository extends JpaRepository<ImageEntity, Long> {
}
