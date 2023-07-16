package com.example.cinemaProject.service;

import com.example.cinemaProject.model.Bilet;
import com.example.cinemaProject.model.CosCumparaturi;
import com.example.cinemaProject.model.Movie;
import com.example.cinemaProject.repository.CosCumparaturiRepository;
import com.example.cinemaProject.repository.MovieRepository;
import com.example.cinemaProject.service.Implementare.CosCumparaturiServiceImplementare;
import com.example.cinemaProject.service.Implementare.MovieServiceImplementare;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CosCumparaturiServiceTest {


    private CosCumparaturiServiceImplementare cosCumparaturiServiceImplementare;

    @Mock
    private CosCumparaturiRepository cosCumparaturiRepository;

    private CosCumparaturi cosCumparaturi;

    @BeforeEach
    void init()
    {
        initMocks(this);
        List<CosCumparaturi> listaC=new ArrayList<>();
        CosCumparaturi cosCumparaturi=new CosCumparaturi();
        List<Bilet> lista=new ArrayList<Bilet>();
        Bilet b=new Bilet();
        b.setPret(60);
        lista.add(b);
        cosCumparaturi.setBileteDinCosCumparaturi(lista);
        listaC.add(cosCumparaturi);
        when(cosCumparaturiRepository.findAll()).thenReturn(listaC);
    }

   /* @Test
    void givenCosCumparaturi_whenfindBileteUnderASUm_thenFindOne()
    {
        cosCumparaturiServiceImplementare=new
                CosCumparaturiServiceImplementare(cosCumparaturiRepository);

        List<Bilet> lista1=cosCumparaturiServiceImplementare.findBileteUnderASUm(90);

        assertNotNull(lista1);
        assertEquals(lista1.get(0).getPret(),60);
    }*/


    @Test
    void givenCosCumparaturi_whenfindBileteUnderASUm_thenThrowException()
    {
        when(cosCumparaturiRepository.findAll()).thenReturn(null);

        Exception excp=assertThrows(NullPointerException.class,()->
        {
            cosCumparaturiServiceImplementare.findBileteUnderASUm(90);
        });
    }

}
