package com.example.cinemaProject.repository;

import com.example.cinemaProject.model.Movie;
import com.example.cinemaProject.model.Review;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review,Long> {
    List<Review> findAllByStele(int nr);
}
