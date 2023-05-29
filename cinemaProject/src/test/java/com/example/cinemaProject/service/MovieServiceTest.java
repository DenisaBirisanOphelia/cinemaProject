package com.example.cinemaProject.service;

import com.example.cinemaProject.DTO.MovieDTO;
import com.example.cinemaProject.model.Movie;
import com.example.cinemaProject.repository.MovieRepository;
import com.example.cinemaProject.service.Implementare.MovieServiceImplementare;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.stubbing.Answer;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class MovieServiceTest {

    private static final String EXISTENT_MOVIE="Coherence";
    private static final String NONEXISTENT_MOVIE="Rapunzel";

    private MovieServiceImplementare movieServiceImplementare;

    @Mock
    private MovieRepository movieRepository;

    private Movie movie;

    @BeforeEach
    void init()
    {
        initMocks(this);
        Movie movie=new Movie();
        movie.setName(EXISTENT_MOVIE);
        movie.setId(3L);
        when(movieRepository.findFirstByName(EXISTENT_MOVIE)).thenReturn( movie);

    }

    @Test
    void givenExistentMovie_whenfindFirstByName_thenFindOne()
    {
        movieServiceImplementare=new MovieServiceImplementare(movieRepository);

        MovieDTO movie=movieServiceImplementare.findByName(EXISTENT_MOVIE);

        assertNotNull(movie);
        assertEquals(movie.getName(),EXISTENT_MOVIE);
    }


    @Test
    void givenNonExistentMovie_whenfindFirstByName_thenThrowException()
    {
        when(movieRepository.findFirstByName(NONEXISTENT_MOVIE)).thenReturn(null);

        Exception excp=assertThrows(NullPointerException.class,()->
        {
            movieServiceImplementare.findByName(NONEXISTENT_MOVIE);
        });
    }
    @Test
    void deleteMovieDupaNumeExistent()
    {   movie=new Movie();
        movie.setName(EXISTENT_MOVIE);
        movie.setId(3L);

        when(movieRepository.findFirstByName(movie.getName())).thenReturn(movie);
        movieServiceImplementare=new MovieServiceImplementare(movieRepository);

     //   movieServiceImplementare.deleteMovie(movie.getName());
       verify(movieRepository).deleteById(movie.getId());
    }
    @Test
    void deleteMovieDupaNumeNonExistent()
    {   movie=new Movie();
        movie.setName(NONEXISTENT_MOVIE);
        movie.setId(3L);

        when(movieRepository.findFirstByName(NONEXISTENT_MOVIE)).thenReturn(null);

        Exception excp=assertThrows(NullPointerException.class,()->
        {
       //     movieServiceImplementare.deleteMovie(NONEXISTENT_MOVIE);
        });
    }
    @Test
    void updateMovieRegizor()
    {   movie=new Movie();
        movie.setName(EXISTENT_MOVIE);
        movie.setId(3L);

        when(movieRepository.findById(movie.getId())).thenReturn(Optional.ofNullable(movie));

        movieServiceImplementare=new MovieServiceImplementare(movieRepository);


        Movie movie1=movieServiceImplementare.updateMovie(movie,"Spillberg");
        assertNotNull(movie1);
        assertEquals(movie1.getRegizor(),"Spillberg");

    }
}
