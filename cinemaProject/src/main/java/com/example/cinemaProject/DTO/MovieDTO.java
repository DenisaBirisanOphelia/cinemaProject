package com.example.cinemaProject.DTO;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MovieDTO {

    private Long id;
    private String name;
    private String regizor;
    private int rating;
    private String imageSrc;
    private LocalDateTime data;
    private int pret;
    private boolean isFavorited;


}
