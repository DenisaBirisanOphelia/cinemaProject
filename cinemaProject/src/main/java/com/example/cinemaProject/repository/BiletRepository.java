package com.example.cinemaProject.repository;

import com.example.cinemaProject.model.Bilet;
import com.example.cinemaProject.model.CosCumparaturi;
import com.example.cinemaProject.model.Movie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BiletRepository extends CrudRepository<Bilet,Long> {
    Bilet findByPretAndFilmDinBiletAndLocInSala(int pret, Movie film, int loc);
    Bilet findFirstByFilmDinBilet(Movie movie);
    List<Bilet> findAllByFilmDinBilet(Movie movie);
    Bilet findFirstById(Long id);
    Bilet findFirstByFilmDinBiletAndLocInSala(Movie movie,String loc);

  Bilet findFirstByCosCumparaturi(CosCumparaturi c);
}
