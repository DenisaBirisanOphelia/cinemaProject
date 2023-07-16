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
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int stele;
    private String comentariu;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name="fk_movie_reviws_sale")
   private Movie filmReviewed;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="fk_review_of_client")
    private Client clientulCeADatReview;
}
