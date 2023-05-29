package com.example.cinemaProject.DTO;

import com.example.cinemaProject.model.Movie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BiletDTO {
    private Long id;
    private int pret;
    private String locInSala;
}
