package com.example.cinemaProject.controller;

import com.example.cinemaProject.DTO.MovieDTO;
import com.example.cinemaProject.DTO.WatchListDTO;
import com.example.cinemaProject.service.MovieService;
import com.example.cinemaProject.service.WatchListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/watchList")
public class WatchListController {

    @Autowired
    private WatchListService watchListService;

    @Autowired
    private MovieService movieService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/addToWatchList")
    public WatchListDTO addMovieToWatchList(@RequestParam String nume
    ,@RequestParam String regizor,@RequestParam String nume1,
                                            @RequestParam String prenume) {
       return watchListService.saveMovieInWatchListForClient(nume,regizor,nume1,prenume);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getMoviesFromWatchList")
    public List<MovieDTO> getMFW(@RequestParam String nume, @RequestParam String prenume) {
        return watchListService.getMoviesForClient(nume,prenume);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/deleteMovieFromWatchList")
    public void deleteMFW(@RequestParam String nume, @RequestParam String regizor,
                          @RequestParam String nume1,@RequestParam String prenume) {
         watchListService.deleteMovieFromWatchList(nume,regizor,nume1,prenume);
    }


}
