package com.example.cinemaProject.mapper;

import com.example.cinemaProject.DTO.ReviewDTO;
import com.example.cinemaProject.DTO.WatchListDTO;
import com.example.cinemaProject.model.Review;
import com.example.cinemaProject.model.WatchList;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {

    public static ReviewDTO mapModelToDto(Review r){
        ReviewDTO reviewDTO=new ReviewDTO();
        reviewDTO.setId(r.getId());
        reviewDTO.setStele(r.getStele());
        reviewDTO.setComentariu(r.getComentariu());

        return reviewDTO;
    }

    public static Review mapDtoToModel(ReviewDTO reviewDTO){
        Review r=new Review();
        r.setId(reviewDTO.getId());
        r.setStele(reviewDTO.getStele());
        r.setComentariu(reviewDTO.getComentariu());
        return r;
    }
}
