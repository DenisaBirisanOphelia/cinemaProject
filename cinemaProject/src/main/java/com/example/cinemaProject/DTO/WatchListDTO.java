package com.example.cinemaProject.DTO;

import com.example.cinemaProject.model.Movie;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class WatchListDTO {
    private Long id;
    private String numeWatchList;
}
