package com.example.cinemaProject.mapper;

import com.example.cinemaProject.DTO.BiletDTO;
import com.example.cinemaProject.DTO.MovieDTO;
import com.example.cinemaProject.model.Bilet;
import com.example.cinemaProject.model.Movie;
import org.springframework.stereotype.Component;

@Component
public class BiletMapper {
    public static BiletDTO mapModelToDto(Bilet bilet){
        BiletDTO biletDTO=new BiletDTO();
        biletDTO.setId(bilet.getId());
        biletDTO.setPret(bilet.getPret());
        biletDTO.setLocInSala(bilet.getLocInSala());
        return biletDTO;
    }

    public static Bilet mapDtoToModel(BiletDTO biletDTO){
        Bilet bilet=new Bilet();
        bilet.setId(biletDTO.getId());
        bilet.setPret(biletDTO.getPret());
        bilet.setLocInSala(biletDTO.getLocInSala());
        return bilet;
    }
}