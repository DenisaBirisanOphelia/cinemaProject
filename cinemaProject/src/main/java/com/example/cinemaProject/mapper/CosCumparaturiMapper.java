package com.example.cinemaProject.mapper;

import com.example.cinemaProject.DTO.CosCumparaturiDTO;
import com.example.cinemaProject.DTO.MovieDTO;
import com.example.cinemaProject.model.CosCumparaturi;
import com.example.cinemaProject.model.Movie;
import org.springframework.stereotype.Component;

@Component
public class CosCumparaturiMapper {
    public static CosCumparaturiDTO mapModelToDto(CosCumparaturi c){
        CosCumparaturiDTO cosCumparaturiDTO=new CosCumparaturiDTO();
        cosCumparaturiDTO.setId(c.getId());
        cosCumparaturiDTO.setPretTotal(c.getPretTotal());
        return cosCumparaturiDTO;
    }

    public static CosCumparaturi mapDtoToModel(CosCumparaturiDTO cosCumparaturiDTO){
        CosCumparaturi c=new CosCumparaturi();
        c.setId(cosCumparaturiDTO.getId());
        c.setPretTotal(cosCumparaturiDTO.getPretTotal());
        return c;
    }
}
