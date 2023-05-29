package com.example.cinemaProject.repository;

import com.example.cinemaProject.model.Bilet;
import com.example.cinemaProject.model.Client;
import com.example.cinemaProject.model.CosCumparaturi;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CosCumparaturiRepository extends CrudRepository<CosCumparaturi,Long> {
    List<CosCumparaturi> findAllByClient(Client c);
    CosCumparaturi findFirstByBileteDinCosCumparaturi(Bilet b);
}
