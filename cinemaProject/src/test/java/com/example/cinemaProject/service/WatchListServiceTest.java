package com.example.cinemaProject.service;

import com.example.cinemaProject.model.Client;
import com.example.cinemaProject.model.Movie;
import com.example.cinemaProject.model.WatchList;
import com.example.cinemaProject.repository.MovieRepository;
import com.example.cinemaProject.repository.WatchListRepository;
import com.example.cinemaProject.service.Implementare.MovieServiceImplementare;
import com.example.cinemaProject.service.Implementare.WatchListImplementare;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class WatchListServiceTest {

    private WatchListImplementare watchListImplementare;

    @Mock
    private WatchListRepository watchListRepository;

    private WatchList watchList;

    @BeforeEach
    void init()
    {
        initMocks(this);
        WatchList watchList=new WatchList();
        Set<Movie> lista=new HashSet<>();
        Movie m=new Movie();
        m.setName("Coherence");
        Movie m2=new Movie();
        m2.setName("Teambuilding");
        lista.add(m);
        lista.add(m2);
        watchList.setNumeWatchList("First watchList");
        watchList.setListaFilmeDeVazut(lista);
        List<WatchList> list=new ArrayList<>();
        list.add(watchList);
        when(watchListRepository.findAll()).thenReturn(list );
    }

    @Test
    void givenExistentWatchlist_whenfindWatchListContainingMovie_thenFindOne()
    {
        watchListImplementare=new WatchListImplementare(watchListRepository);

        Movie m2=new Movie();
        m2.setName("Teambuilding");
        Set<WatchList> lista1=watchListImplementare.findWatchListContainingMovie(m2);

        List<WatchList> l=new ArrayList<>();
        lista1.forEach(w->
        {
            l.add(w);
        });
        assertNotNull(lista1);
        assertEquals(l.get(0).getNumeWatchList(),"First watchList");
    }


    @Test
    void givenNonExistentWatchlist_whenfindWatchListContainingMovie_thenThrowException()
    {
        when(watchListRepository.findAll()).thenReturn(null);

        Exception excp=assertThrows(NullPointerException.class,()->
        { Movie m3=new Movie();
            m3.setName("Ciresarii");
            watchListImplementare.findWatchListContainingMovie(m3);
        });
    }


}
