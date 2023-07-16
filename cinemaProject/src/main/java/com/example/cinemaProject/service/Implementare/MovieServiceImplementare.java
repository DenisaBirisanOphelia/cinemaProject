package com.example.cinemaProject.service.Implementare;

import com.example.cinemaProject.DTO.MovieDTO;
import com.example.cinemaProject.mapper.MovieMapper;
import com.example.cinemaProject.model.Bilet;
import com.example.cinemaProject.model.Movie;
import com.example.cinemaProject.model.MovieComparator;
import com.example.cinemaProject.model.WatchList;
import com.example.cinemaProject.repository.MovieRepository;
import com.example.cinemaProject.service.MovieService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public  class MovieServiceImplementare implements MovieService {
    @Autowired
    private MovieRepository movieRepo;

    public MovieServiceImplementare(MovieRepository movieRepo) {
        this.movieRepo = movieRepo;
    }

    @Override
    public Movie saveMovie(Movie film) {
        return movieRepo.save(film);
    }

    @Override
    public MovieDTO findByName(String nume) {
        return MovieMapper.mapModelToDto(movieRepo.findFirstByName(nume));
    }

    @Override
    public Movie updateMovie(Movie film, String reg) {
        Movie updateMovie = movieRepo.findById(film.getId()).get();
        if(updateMovie==null) return null;
        updateMovie.setRegizor(reg);
        movieRepo.save(updateMovie);

        return updateMovie;
    }

    @Override
    public MovieDTO uploadPhoto(String nume, MultipartFile photo) throws IOException {
            byte[] photoBytes = photo.getBytes();
            Movie movie=movieRepo.findFirstByName(nume);
          //  movie.setPhotoData(photoBytes);
            movieRepo.save(movie);
            return MovieMapper.mapModelToDto(movie);
    }


    @Override
    public List<MovieDTO> findChosenOrderMovies(String order,String criteriu)  {
        List<MovieDTO> rez=new ArrayList<>();
        List<Movie> allMovies=movieRepo.findAll();

        if(order.equals("Ascending"))  allMovies.sort(new MovieComparator(criteriu));
       else
            if (order.equals("Descending")) {
                allMovies.sort(new MovieComparator(criteriu));
                Collections.reverse(allMovies);
            }

        for(Movie movie:allMovies) {
            rez.add(MovieMapper.mapModelToDto(movie));
        }
        return rez;
    }
    @Override
    public Movie updateMovieWatchList(Movie film,WatchList watchList) {
        //aici sterg vechea valoare din watchlist,ca n are de unde sti sa se propage asta
        Movie updateMovie = movieRepo.findFirstByName(film.getName());
        //daca fac asa si sterg prima intrare, pot sa scap de valorile alea dublate de genu:
        //pt un movie caruia ii udpatez watchlistu, imi raman si vechile intrari de watchlist
        //inclusiv cea initiala de null cu toate adnotarile vietii
        movieRepo.delete(updateMovie);
        updateMovie.setWatchList(watchList);
        movieRepo.save(updateMovie);

        return updateMovie;
    }
/*
    @Override
    public Movie updateMovieBiletPretAndLoc(Movie film, int pretNou, int locNou) {
        Movie updateMovie=movieRepo.findFirstByName(film.getName());
        movieRepo.delete(updateMovie);
        //Bilet b=updateMovie.getBiletPtFilm();
        b.setPret(pretNou);
       // b.setLocInSala(locNou);
        updateMovie.setBiletPtFilm(b);
        movieRepo.save(updateMovie);
        return  updateMovie;
    }


    @Override
    public void deleteMovie(String nume) {
       //cd sterg un movie vr sa se stearga si intrarea null pt el din WatchList
        movieRepo.deleteById(movieRepo.findFirstByName(nume).getId());
    }

    @Override
    public void deleteBiletForMovie(Movie film) {
        this.updateMovieBiletPretAndLoc(film,-1,-1);
    }

    @Override
    public List<Movie> findAllMoviesInWatchList(WatchList watchList) {
        return movieRepo.findAllMoviesByWatchList(watchList);
    }
*/
    @Override
    public List<MovieDTO> findAllMovies() {
        List<MovieDTO> lista=new ArrayList<>();
        for(Movie movie:movieRepo.findAll()) {
            lista.add(MovieMapper.mapModelToDto(movie));
        }
        return lista;
    }

    @Override
    public String getMovieForImage(String nume) {
        if(movieRepo==null)
            return null;
        Movie movie = movieRepo.findFirstByName(nume);
      //  if (movie != null) {
       //     byte[] imageBlob = movie.getPhotoData();
       //   String base64Image = Base64.getEncoder().encodeToString(imageBlob);
      //      return base64Image;
     //   }
      // else return null;
        return null;
    }

    @Override
    public List<MovieDTO> findChosenMovies(String filterOption, String word) throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Field field = Movie.class.getDeclaredField(filterOption);

        // Generate the getter method name based on the property name
        String getterName = "get" + filterOption.substring(0, 1).toUpperCase() + filterOption.substring(1);

        // Use reflection to generate the getter method
        Method getter = Movie.class.getDeclaredMethod(getterName);

        List<MovieDTO> rez=new ArrayList<>();
        List<Movie> allMovies=movieRepo.findAll();

        for(Movie m:allMovies) {
            String propertyValue = (String) getter.invoke(m);
            if(propertyValue.equals(word)) rez.add(MovieMapper.mapModelToDto(m));
        }
        return rez;
    }


    @Override
    public MovieDTO updateMovieByName(String name, String updateOption, Object newValue) throws Exception {
        Movie m=movieRepo.findFirstByName(name);
        //movieRepo.delete(m);//sa nu fie duplicate
    if(m==null) return null;
        m.createSetter(updateOption, m, newValue);
        movieRepo.save(m);
        return MovieMapper.mapModelToDto(m);
    }

    @Override
    public void deleteMovieByField(String deleteOption, String value) {
        if(deleteOption.equals("name")) {
            Movie m=movieRepo.findFirstByName(value);
            if(m==null ) return;
            movieRepo.deleteById(m.getId());
        }

        else if (deleteOption.equals("regizor")) {
            Movie m=movieRepo.findFirstByRegizor(value);
            if(m==null ) return;
             movieRepo.deleteById(m.getId());
        }
    }

    @Override
    public void deleteMovieByField(String deleteOption, int value) {
        Movie m=movieRepo.findFirstByRating(value);
        if(m==null) return;
            movieRepo.deleteById(m.getId());
    }

    @Override
    public MovieDTO addImage(String nume, String r, String url) {
         Movie m=movieRepo.findFirstByNameAndRegizor(nume,r);
         if(m==null) return null;
         m.setImageSrc(url);
         movieRepo.save(m);
         return MovieMapper.mapModelToDto(m);
    }

    @Override
    public int getPrice(String numeFilm) {
        Movie m=movieRepo.findFirstByName(numeFilm);
        if(m!=null)
        return m.getPret();
        else return -1;
    }


}
