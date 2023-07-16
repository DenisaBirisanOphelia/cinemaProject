package com.example.cinemaProject.service;

import com.example.cinemaProject.model.Client;
import com.example.cinemaProject.model.Movie;
import com.example.cinemaProject.model.Review;
import com.example.cinemaProject.repository.MovieRepository;
import com.example.cinemaProject.repository.ReviewRepository;
import com.example.cinemaProject.service.Implementare.ClientServiceImplementare;
import com.example.cinemaProject.service.Implementare.MovieServiceImplementare;
import com.example.cinemaProject.service.Implementare.ReviewServiceImplementare;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ReviewServiceTest {
    private ReviewServiceImplementare reviewServiceImplementare;

    @Mock
    private ReviewRepository reviewRepository;

    private Review review;

    @BeforeEach
    void init()
    {
        initMocks(this);
        review=new Review();
        review.setStele(3);
        List<Review> lista=new ArrayList<Review>();
        lista.add(review);
        //asta mimeaza comportamentu depdentelor externe,gen al repo-ului
        when(reviewRepository.findAllByStele(3)).thenReturn(lista);
        when(reviewRepository.findAll()).thenReturn(lista);
    }

    @Test
    void givenExistentReview_whenfindByStele_thenFindOne()
    {
        reviewServiceImplementare=new ReviewServiceImplementare(reviewRepository);

        List<Review> review1=reviewServiceImplementare.findByStele(3);

        assertNotNull(review1);
        assertEquals(review,review);
    }

    @Test
    void givenNonExistentReview_whenfindByStele_thenThrowException()
    {
        when(reviewRepository.findAllByStele(3)).thenReturn(null);

        Exception excp=assertThrows(NullPointerException.class,()->
        {
            reviewServiceImplementare.findByStele(3);
        });
    }
    @Test
    void updateReviewComment()
    {   review=new Review();
        review.setStele(3);
        review.setComentariu("NU MI PLACE DE NICI O CULOARE!!");
        when(reviewRepository.findById(review.getId())).thenReturn(Optional.ofNullable(review));

        reviewServiceImplementare=new ReviewServiceImplementare(reviewRepository);

        Review review1=reviewServiceImplementare.updateMovie(review,"M-AM RAZGANDIT," +
                "E FRUMIX");
        assertNotNull(review1);
        assertEquals(review1.getComentariu(),"M-AM RAZGANDIT," +
                "E FRUMIX");
    }

    @Test
    void deleteReviewsCuNrStele_ifFound()
    {   review=new Review();
        review.setStele(3);
    List<Review> lista=new ArrayList<>();
        when(reviewRepository.findAllByStele(3)).thenReturn(lista);
        reviewServiceImplementare=new ReviewServiceImplementare(reviewRepository);

        reviewServiceImplementare.deleteReview(3);
        verify(reviewRepository).deleteAll(lista);
    }
    @Test
    void deleteReviewsCuNrStele_ifNotFound()
    {   review=new Review();
        review.setStele(3);
        List<Review> lista=new ArrayList<>();
        when(reviewRepository.findAllByStele(3)).thenReturn(null);

        Exception excp=assertThrows(NullPointerException.class,()->
        {
            reviewServiceImplementare.deleteReview(3);
        });
    }
}
