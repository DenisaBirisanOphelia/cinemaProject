package com.example.cinemaProject.service;

import com.example.cinemaProject.DTO.MovieDTO;
import com.example.cinemaProject.model.Movie;
import com.example.cinemaProject.model.WatchList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Blob;
import java.util.List;

@Component
public interface MovieService  {

    Movie saveMovie(Movie film);
    MovieDTO findByName(String nume);
    Movie updateMovie(Movie film,String reg);
    MovieDTO uploadPhoto(String nume, MultipartFile photo) throws IOException;
    Movie updateMovieWatchList(Movie film,WatchList watchList);
    //Movie updateMovieBiletPretAndLoc(Movie film,int pretNou, int locNou);
    //void deleteMovie(String nume);
   // void deleteBiletForMovie(Movie film);
    List<MovieDTO> findChosenOrderMovies(String order,String criteriu);

   // List<Movie> findAllMoviesInWatchList(WatchList byClient);

    List<MovieDTO> findAllMovies();

    String getMovieForImage(String nume);

    List<MovieDTO> findChosenMovies(String filterOption, String word) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException;

    MovieDTO updateMovieByName(String name, String updateOption, Object newValue) throws Exception;

    void deleteMovieByField(String deleteOption, String value);
    void deleteMovieByField(String deleteOption, int value);

    MovieDTO addImage(String nume,String r,String url);

    int getPrice(String numeFilm);
}
