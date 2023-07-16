package com.example.cinemaProject.mapper;

import com.example.cinemaProject.DTO.MovieDTO;
import com.example.cinemaProject.DTO.WatchListDTO;
import com.example.cinemaProject.model.Movie;
import com.example.cinemaProject.model.WatchList;
import org.springframework.stereotype.Component;

@Component
public class WatchListMapper {
    public static WatchListDTO mapModelToDto(WatchList w){
        WatchListDTO watchListDTO=new WatchListDTO();
        watchListDTO.setId(w.getId());
        watchListDTO.setNumeWatchList(w.getNumeWatchList());

        return watchListDTO;
    }

    public static WatchList mapDtoToModel(WatchListDTO watchListDTO){
        WatchList w=new WatchList();
        w.setId(watchListDTO.getId());
        w.setNumeWatchList(watchListDTO.getNumeWatchList());
        return w;
    }
}
