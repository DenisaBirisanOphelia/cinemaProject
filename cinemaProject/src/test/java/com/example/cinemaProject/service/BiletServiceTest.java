package com.example.cinemaProject.service;

import com.example.cinemaProject.model.Bilet;
import com.example.cinemaProject.model.Movie;
import com.example.cinemaProject.repository.BiletRepository;
import com.example.cinemaProject.repository.MovieRepository;
import com.example.cinemaProject.service.Implementare.BiletServiceImplementare;
import com.example.cinemaProject.service.Implementare.MovieServiceImplementare;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class BiletServiceTest {

    private static final String EXISTENT_MOVIE="Lala Band the musical";
    private static final String NONEXISTENT_MOVIE="Peter Pan";
    private BiletServiceImplementare biletServiceImplementare;

    @Mock
    private BiletRepository biletRepository;

    private Bilet bilet;


    @BeforeEach
    void init()
    {
        initMocks(this);
        Bilet bilet1=new Bilet();
        Movie m=new Movie();
        m.setName(EXISTENT_MOVIE);
        bilet1.setFilmDinBilet(m);
        bilet1.setPret(45);
     //   bilet1.setLocInSala(113);
        when(biletRepository.findByPretAndFilmDinBiletAndLocInSala(
                45,m,113)).thenReturn( bilet1);
    }

    @Test
    void givenExistentBilet_whenfindBiletByNamePriceLoc_thenFindOne()
    {
        biletServiceImplementare=new BiletServiceImplementare(biletRepository);

        Movie m2=new Movie();
        m2.setName(EXISTENT_MOVIE);
        Bilet bilet=biletServiceImplementare.findBiletByNamePriceLoc(m2,
                45,113);

        assertNotNull(bilet);
        assertEquals(bilet.getFilmDinBilet(),m2);
    }


    @Test
    void givenNonExistentBilet_whenfindBiletByNamePriceLoc_thenThrowException()
    {
        Movie m2=new Movie();
        m2.setName(NONEXISTENT_MOVIE);
        when(biletRepository.findByPretAndFilmDinBiletAndLocInSala(
                20,m2,113)).thenReturn(null);

        Exception excp=assertThrows(NullPointerException.class,()->
        {
            biletServiceImplementare.findBiletByNamePriceLoc(m2,20,113);
        });
    }
    @Test
    void deleteBiletExistent()
    {  Bilet bilet1=new Bilet();
        Movie m=new Movie();
        m.setName(EXISTENT_MOVIE);
        bilet1.setFilmDinBilet(m);
        bilet1.setPret(45);
      //  bilet1.setLocInSala(113);
        biletServiceImplementare=new BiletServiceImplementare(biletRepository);

        biletServiceImplementare.deleteBilet(bilet1);
        verify(biletRepository).delete(bilet1);
    }
    @Test
    void deleteBiletNonExistent()
    {   Bilet bilet1=new Bilet();
        Movie m=new Movie();
        m.setName(EXISTENT_MOVIE);
        bilet1.setFilmDinBilet(m);
        bilet1.setPret(45);
       // bilet1.setLocInSala(113);

        Exception excp=assertThrows(NullPointerException.class,()->
        {
            biletServiceImplementare.deleteBilet(bilet1);
        });
    }
    @Test
    void updatePretBilet()
    {
        Bilet bilet1=new Bilet();
        Movie m=new Movie();
        m.setName(EXISTENT_MOVIE);
        bilet1.setFilmDinBilet(m);
        bilet1.setPret(45);
        ///bilet1.setLocInSala(113);
        when(biletRepository.findById(bilet1.getId())).thenReturn(Optional.ofNullable(bilet1));

        biletServiceImplementare=new BiletServiceImplementare(biletRepository);

        Bilet bilet2=biletServiceImplementare.updatePretBilet(bilet1,70);
        assertNotNull(bilet2);
        assertEquals(bilet2.getPret(),70);

    }
}
