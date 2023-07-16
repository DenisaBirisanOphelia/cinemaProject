package com.example.cinemaProject.service;

import com.example.cinemaProject.DTO.BiletDTO;
import com.example.cinemaProject.DTO.CosCumparaturiDTO;
import com.example.cinemaProject.model.Bilet;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CosCumparaturiService {
    CosCumparaturiDTO addBiletToCosCumparaturi(Long id, String nume, String prenume);

    //asta nu e deloc utila
    List<Bilet> findBileteUnderASUm(int sum);
    List<CosCumparaturiDTO> deleteAllFromCosCumparaturi(String nume, String prenume);

    CosCumparaturiDTO removeBiletFromCosCumparaturi(String numeFilm, String regizor,String loc, String nume, String prenume);

    List<CosCumparaturiDTO> findAll();

    List<BiletDTO> getAllBilete();
}
