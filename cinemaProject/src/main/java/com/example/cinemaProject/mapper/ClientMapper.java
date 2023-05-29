package com.example.cinemaProject.mapper;

import com.example.cinemaProject.DTO.ClientDTO;
import com.example.cinemaProject.DTO.MovieDTO;
import com.example.cinemaProject.model.Client;
import com.example.cinemaProject.model.Movie;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {
    public static ClientDTO mapModelToDto(Client client){
        ClientDTO clientDTO=new ClientDTO();
        clientDTO.setId(client.getId());
        clientDTO.setNume(client.getNume());
        clientDTO.setPrenume(client.getPrenume());
        clientDTO.setPassword(client.getPassword());
        clientDTO.setEmail(client.getEmail());
        clientDTO.setAge(client.getAge());
        clientDTO.setConfirmedClient(client.getConfirmedClient());
        clientDTO.setLoggedClient(client.getLoggedClient());

        return clientDTO;
    }

    public static Client mapDtoToModel(ClientDTO clientDTO){
        Client client=new Client();
        client.setNume(clientDTO.getNume());
        client.setId(clientDTO.getId());
        client.setPrenume(clientDTO.getPrenume());
        client.setPassword(clientDTO.getPassword());
        client.setEmail(clientDTO.getEmail());
        client.setAge(clientDTO.getAge());
        client.setConfirmedClient(clientDTO.getConfirmedClient());
        client.setLoggedClient(clientDTO.getLoggedClient());

        return client;
    }
}
