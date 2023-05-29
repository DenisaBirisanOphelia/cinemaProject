package com.example.cinemaProject.mapper;

import com.example.cinemaProject.DTO.AdminDTO;
import com.example.cinemaProject.DTO.ClientDTO;
import com.example.cinemaProject.model.Admin;
import com.example.cinemaProject.model.Client;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper {
    public static AdminDTO mapModelToDto(Admin client){
        AdminDTO clientDTO=new AdminDTO();
        clientDTO.setId(client.getId());
        clientDTO.setNume(client.getNume());
        clientDTO.setPrenume(client.getPrenume());
        clientDTO.setPassword(client.getPassword());
        clientDTO.setEmail(client.getEmail());
        clientDTO.setAge(client.getAge());

        return clientDTO;
    }

    public static Admin mapDtoToModel(AdminDTO clientDTO){
        Admin client=new Admin();
        client.setNume(clientDTO.getNume());
        client.setId(clientDTO.getId());
        client.setPrenume(clientDTO.getPrenume());
        client.setPassword(clientDTO.getPassword());
        client.setEmail(clientDTO.getEmail());
        client.setAge(clientDTO.getAge());

        return client;
    }
}
