package com.example.cinemaProject.repository;

import com.example.cinemaProject.model.Movie;
import com.example.cinemaProject.model.WatchList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {
    Movie findFirstByName(String nume);
    List<Movie> findAllMoviesByWatchList(WatchList watchList);
    Movie findFirstByRegizor(String nume);
    Movie findFirstByRating(int nr);
    Movie findFirstByNameAndRegizor(String nume,String regizor);

}
