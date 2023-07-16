package com.example.cinemaProject.controller;

import com.example.cinemaProject.DTO.BiletDTO;
import com.example.cinemaProject.DTO.MovieDTO;
import com.example.cinemaProject.model.Bilet;
import com.example.cinemaProject.model.Movie;
import com.example.cinemaProject.service.BiletService;
import com.example.cinemaProject.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bilet")
public class BiletController {
    @Autowired
    private BiletService biletService;


    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/addBilet")
    public BiletDTO addBiletReqParam(@RequestParam String nume,
            @RequestParam String locInSala, @RequestParam int pret) {
        //locul il voi lua din front end, la fel si pretul, dar before
        //din cauza relatiilor mele din be, le las pret -1 si loc -- si fac validarile in ts din fe
    return biletService.createBiletByNameAndRegizor(nume,locInSala,pret);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getMovieFromBilet")
    public MovieDTO getMovieFromBilet(@RequestParam Long id)
    {
        return biletService.getMovieFromBilet(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/findBileteByNumeFilm")
    public List<BiletDTO> findBiletByNumeFilmReqParam(@RequestParam String nume) {
        //locul il voi lua din front end, la fel si pretul, dar before
        //din cauza relatiilor mele din be, le las pret -1 si loc -- si fac validarile in ts din fe
        return biletService.findBileteByNumeFilm(nume);
    }

}
