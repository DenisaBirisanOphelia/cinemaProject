package com.example.cinemaProject.service;

import com.example.cinemaProject.DTO.ClientDTO;
import com.example.cinemaProject.model.Client;
import com.example.cinemaProject.model.Movie;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ClientService {
    ClientDTO addClient(String nume,String prenume,String pass,String email);
    ClientDTO logIn(String nume, String prenume,String password);
    ClientDTO findByNumePrenume(String nume, String prenume );
    ClientDTO findByNameOnly(String nume);
    ClientDTO updateClient(String email);
    //asta e inutila ca se face automat
    Client updateClientAddMovieToWatchList(Client client, Movie film);
    //asta e leg pe care o doresc, cred
    void updateClientAddMovieToListaMoviesCurente(Client client, Movie film);
    void deleteClient(String nume,String prenume);

    List<ClientDTO> findAll();


    ClientDTO logOut(String nume,String prenume,String email);

    List<ClientDTO> loggedClients();

    ClientDTO findByNumePrenumePass(String nume, String prenume, String pass);
}
