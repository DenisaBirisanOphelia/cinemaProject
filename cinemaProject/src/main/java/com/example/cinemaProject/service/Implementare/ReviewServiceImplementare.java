package com.example.cinemaProject.service.Implementare;

import com.example.cinemaProject.DTO.ClientDTO;
import com.example.cinemaProject.DTO.MovieDTO;
import com.example.cinemaProject.DTO.ReviewDTO;
import com.example.cinemaProject.mapper.ClientMapper;
import com.example.cinemaProject.mapper.MovieMapper;
import com.example.cinemaProject.mapper.ReviewMapper;
import com.example.cinemaProject.model.Client;
import com.example.cinemaProject.model.Movie;
import com.example.cinemaProject.model.Review;
import com.example.cinemaProject.repository.ClientRepository;
import com.example.cinemaProject.repository.MovieRepository;
import com.example.cinemaProject.repository.ReviewRepository;
import com.example.cinemaProject.service.ReviewService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReviewServiceImplementare implements ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ClientRepository clientRepository;

    public ReviewServiceImplementare(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> findByStele(int nr) {
        List<Review> lista=new ArrayList<Review>();
        for(Review r: reviewRepository.findAll())
        {
            if (r.getStele()==nr) lista.add(r);
        }
        return lista;
    }

    @Override
    public Review updateMovie(Review review, String commentNou) {
        Review updateReview = reviewRepository.findById(review.getId()).get();
        updateReview.setComentariu(commentNou);
        reviewRepository.save(updateReview);

        return updateReview;
    }

    @Override
    public void deleteReview(int nr) {
        reviewRepository.deleteAll(reviewRepository.findAllByStele(nr));
    }

    @Override
    public ReviewDTO addReview(String nume, String regizor, int stele, String comentariu,
                               String numeClient, String prenumeClient) {
        Movie m=movieRepository.findFirstByNameAndRegizor(nume,regizor);
        if(m==null) return null;
        Review r=new Review();
        r.setStele(stele);
        r.setFilmReviewed(m);
        r.setComentariu(comentariu);

        //il leg de client aicea
        Client c=clientRepository.findFirstByNumeAndPrenume(numeClient,prenumeClient);
        if(c==null) return null;
        List<Review> lista=c.getListaReviews();
        lista.add(r);
        c.setListaReviews(lista);


        r.setClientulCeADatReview(c);
        reviewRepository.save(r);
        clientRepository.save(c);





        //plus trebe sa creasca nr stele pt movie-ul respectiv
        int nr=(m.getRating()+stele)/2;

        m.setRating(nr);
        movieRepository.save(m);


        return ReviewMapper.mapModelToDto(r);
    }

    @Override
    public List<ReviewDTO> getAllReviews() {
        List<ReviewDTO> lista=new ArrayList<>();
        List<Review> a= (List<Review>) reviewRepository.findAll();
         for(Review r:a)
         {
             lista.add(ReviewMapper.mapModelToDto(r));
         }
         return lista;
    }

    @Override
    public MovieDTO getMovieFromReview(Long id) {
        Optional<Review> r=reviewRepository.findById(id);
        if(r==null) return null;
        Movie m=r.get().getFilmReviewed();
        if(m==null) return null;
        return MovieMapper.mapModelToDto(m);
    }

    @Override
    public ClientDTO getClientFromReview(Long id) {
        Optional<Review> r=reviewRepository.findById(id);
        if(r==null) return null;
        Client c=r.get().getClientulCeADatReview();
        if(c==null) return null;
        return ClientMapper.mapModelToDto(c);
    }
}
