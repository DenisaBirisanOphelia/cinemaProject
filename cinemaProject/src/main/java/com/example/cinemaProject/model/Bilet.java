package com.example.cinemaProject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Bilet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch=FetchType.EAGER)
    private Movie filmDinBilet;

    private int pret=0;
    private String locInSala="--";

    @ManyToOne(fetch=FetchType.EAGER,cascade = CascadeType.REMOVE)
    @JoinColumn(name="fk_bilet_to_cosCumparaturi")
    private CosCumparaturi cosCumparaturi;
}
