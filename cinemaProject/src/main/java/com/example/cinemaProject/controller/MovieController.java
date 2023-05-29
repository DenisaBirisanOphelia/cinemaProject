package com.example.cinemaProject.controller;


import com.example.cinemaProject.DTO.MovieDTO;
import com.example.cinemaProject.mapper.MovieMapper;
import com.example.cinemaProject.model.Movie;
import com.example.cinemaProject.model.WatchList;
import com.example.cinemaProject.service.BiletService;
import com.example.cinemaProject.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("/movie")

public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping("/find")
    public  MovieDTO findByMarcaReqParam(@RequestParam String nume) {
        return movieService.findByName(nume);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/findAllMovies")
    public  List<MovieDTO> findAllReqParam() {
        return movieService.findAllMovies();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/findChosenOrderMovies")
    public  List<MovieDTO> findAll1ReqParam(@RequestParam String order,
                                            @RequestParam String criteriu)  {
          return movieService.findChosenOrderMovies(order, criteriu);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getPriceForMovie")
    public  int getPricereqParam(@RequestParam String numeFilm)  {
        return movieService.getPrice(numeFilm);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/findChosenMovies")
    public  List<MovieDTO> findAll2ReqParam(@RequestParam String filterOption,
                                            @RequestParam String word)  {
       try {
           return movieService.findChosenMovies(filterOption, word);
       }
       catch(Exception e)
        {
            System.out.println("Something went wrong...");
            return null;
        }
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/addMovie")
    public  MovieDTO  addMovieReqParam(@RequestParam String numeFilm, @RequestParam String regizor,
                                   @RequestParam LocalDateTime data, @RequestParam int pret,
                                   @RequestParam String imageSrc) {
        Movie m=new Movie();
        m.setName(numeFilm);
        m.setRegizor(regizor);
        m.setRating(0);
        m.setImageSrc(imageSrc);
        m.setData(data);
        m.setPret(pret);
         movieService.saveMovie(m);
         return MovieMapper.mapModelToDto(m);
    }


    @PutMapping("/uploadPhoto")
    public MovieDTO uploadPhoto(@RequestParam String nume,@RequestBody  MultipartFile photo) {
        try {
            return movieService.uploadPhoto(nume, photo);
        } catch (Exception e) {
            System.out.println("Error uploading file");
            return null;
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getImageForMovie")
    public String getMovie(@RequestParam String name) {
        return movieService.getMovieForImage(name);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/updateMovie")
    public MovieDTO updateMovie(@RequestParam String name,@RequestParam String updateOption,
                                @RequestParam String newValue)
    {
       try {
           return movieService.updateMovieByName(name, updateOption, newValue);
       }
       catch(Exception e)
       {
           System.out.println("ceva nu a mers bine..");
           return null;
       }
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/updateMovie2")
    public MovieDTO updateMovie2(@RequestParam String name,@RequestParam String updateOption,
                                @RequestParam int newValue)
    {
        try {
            return movieService.updateMovieByName(name, updateOption, newValue);
        }
        catch(Exception e)
        {
            System.out.println("ceva nu a mers bine..");
            return null;
        }
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/deleteMovie")
    public void deleteMovie(@RequestParam String deleteOption,
                                @RequestParam String value)
    {
        try {
             movieService.deleteMovieByField(deleteOption,value);
        }
        catch(Exception e)
        {
            System.out.println("ceva nu a mers bine..");

        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/deleteMovie2")
    public void deleteMovie2(@RequestParam String deleteOption,
                            @RequestParam int value)
    {
        try {
            movieService.deleteMovieByField(deleteOption,value);
        }
        catch(Exception e)
        {
            System.out.println("ceva nu a mers bine..");

        }
    }
}
