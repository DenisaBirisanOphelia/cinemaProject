package com.example.cinemaProject.service.Implementare;

import com.example.cinemaProject.DTO.BiletDTO;
import com.example.cinemaProject.DTO.MovieDTO;
import com.example.cinemaProject.mapper.BiletMapper;
import com.example.cinemaProject.mapper.MovieMapper;
import com.example.cinemaProject.model.Bilet;
import com.example.cinemaProject.model.Movie;
import com.example.cinemaProject.repository.BiletRepository;
import com.example.cinemaProject.repository.MovieRepository;
import com.example.cinemaProject.service.BiletService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class BiletServiceImplementare implements BiletService {
    @Autowired
    private BiletRepository biletRepository;

    @Autowired
    private MovieRepository movieRepository;

    public BiletServiceImplementare(BiletRepository biletRepository) {
        this.biletRepository = biletRepository;
    }

    @Override
    public Bilet saveBilet(Bilet bilet) {
        return biletRepository.save(bilet);
    }

    @Override
    public Bilet findBiletByNamePriceLoc(Movie film, int price, int loc) {
        return biletRepository.findByPretAndFilmDinBiletAndLocInSala(price,film,loc);
    }

    @Override
    public Bilet updatePretBilet(Bilet bilet, int pretNou) {
           Bilet bilet1=biletRepository.findById(bilet.getId()).get();
           //biletRepository.delete(bilet);
        if(bilet1==null) return null;
        else {
            bilet1.setPret(pretNou);
            biletRepository.save(bilet1);
            return bilet1;
        }

    }

    @Override
    public void updateLocBilet(Bilet bilet, int loc) {
        Bilet bilet1=biletRepository.findById(bilet.getId()).get();
        biletRepository.delete(bilet);
     //   bilet1.setLocInSala(loc);
        biletRepository.save(bilet1);
    }

    @Override
    public void deleteBilet(Bilet bilet) {
          biletRepository.delete(bilet);
    }

    @Override
    public BiletDTO createBiletByNameAndRegizor(String name, String locInSala, int pret) {
        Movie movie = movieRepository.findFirstByName(name);
        //gasesc filmul, dat dupa nume din front end
        Bilet bilet = null;
        if (movie != null) {
            bilet = new Bilet();
            bilet.setFilmDinBilet(movie);
            bilet.setLocInSala(locInSala);
            bilet.setPret(pret);
            biletRepository.save(bilet);
        }
        return BiletMapper.mapModelToDto(bilet);
    }

    @Override
    public List<BiletDTO> findBileteByNumeFilm(String nume) {
        Movie movie=movieRepository.findFirstByName(nume);
        if(movie==null) return null;
        List<Bilet> bilete=biletRepository.findAllByFilmDinBilet(movie);
        List<BiletDTO> list=new ArrayList<>();
        for(Bilet b:bilete)
        {
            list.add(BiletMapper.mapModelToDto(b));

        }
        return list;
    }

    @Override
    public MovieDTO getMovieFromBilet(Long id) {
        Bilet b=biletRepository.findFirstById(id);
        if(b==null) return null;
       Movie m= b.getFilmDinBilet();
       if(m==null) return null;
       else
       return MovieMapper.mapModelToDto(m);
    }

}
