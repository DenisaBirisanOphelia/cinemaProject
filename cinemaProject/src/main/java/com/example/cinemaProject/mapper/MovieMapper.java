package com.example.cinemaProject.mapper;

import com.example.cinemaProject.DTO.MovieDTO;
import com.example.cinemaProject.model.Movie;
import org.springframework.stereotype.Component;

@Component
public class MovieMapper {
    public static MovieDTO mapModelToDto(Movie movie){
        MovieDTO masinaDTO=new MovieDTO();
        masinaDTO.setId(movie.getId());
        masinaDTO.setName(movie.getName());
        masinaDTO.setRegizor(movie.getRegizor());
        masinaDTO.setRating(movie.getRating());
        masinaDTO.setImageSrc(movie.getImageSrc());
        masinaDTO.setData(movie.getData());
        masinaDTO.setPret(movie.getPret());
        masinaDTO.setFavorited(movie.isFavorited());

        return masinaDTO;
    }

    public static Movie mapDtoToModel(MovieDTO movieDTO){
        Movie movie=new Movie();
        movie.setName(movieDTO.getName());
        movie.setId(movieDTO.getId());
        movie.setRegizor(movieDTO.getRegizor());
        movie.setRating(movieDTO.getRating());
        movie.setImageSrc(movieDTO.getImageSrc());
        movie.setData(movieDTO.getData());
        movie.setPret(movieDTO.getPret());
        movie.setFavorited(movie.isFavorited());

        return movie;
    }
}
