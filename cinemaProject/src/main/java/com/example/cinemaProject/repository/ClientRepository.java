package com.example.cinemaProject.repository;

import com.example.cinemaProject.model.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends CrudRepository<Client,Long> {
    Client findFirstByNumeAndPrenumeAndPassword(String nume,String prenume,String pass);

    Client findFirstByNumeAndPrenumeAndEmail(String nume,String prenume,String email);
    Client findFirstByNume(String nume);
    Client findFirstByEmail(String email);
    Client findFirstByNumeAndPrenumeAndConfirmedClient(String nume,String prenume,Boolean c);

    Client findFirstByNumeAndPrenume(String nume, String prenume);
    List<Client> findAllByLoggedClient(Boolean loggedClient);
}
