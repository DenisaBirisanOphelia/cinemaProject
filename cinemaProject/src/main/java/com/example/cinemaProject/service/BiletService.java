package com.example.cinemaProject.service;

import com.example.cinemaProject.DTO.BiletDTO;
import com.example.cinemaProject.DTO.MovieDTO;
import com.example.cinemaProject.model.Bilet;
import com.example.cinemaProject.model.CosCumparaturi;
import com.example.cinemaProject.model.Movie;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
public interface BiletService {
    //nu fol asta ca inserez prin Movie
    Bilet saveBilet(Bilet bilet);
    //this i use
    Bilet findBiletByNamePriceLoc(Movie film, int price, int loc);
    //aceste doua iara nu, ca ar strica legatura in parentul Movie =>fail stupid
    //gen ar exista blet disp pt un flm care nu mai exista in baza de date
    Bilet updatePretBilet(Bilet bilet,int pretNou);
    void updateLocBilet(Bilet bilet, int loc);
    //asta da, se poate, dar vr sa nu sterg si din Movie==> un taranism ca voi seta locul
    //ca fiind -1 si pretul tot asa, cumva o validare pt cv inexistent
    //dar tot din movie repo deci niste asta nu o folosesc
    void deleteBilet(Bilet bilet);

    BiletDTO createBiletByNameAndRegizor(String nume, String locInSala, int pret);

    List<BiletDTO> findBileteByNumeFilm(String nume);

    MovieDTO getMovieFromBilet(Long id);
}
