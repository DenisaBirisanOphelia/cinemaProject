package com.example.cinemaProject.controller;

import com.example.cinemaProject.DTO.ClientDTO;
import com.example.cinemaProject.model.Client;
import com.example.cinemaProject.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/client")

public class ClientController {
    @Autowired
    private ClientService clientService;
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/findByNumePrenume")
    public ClientDTO findByNumePrenumeReqParam(@RequestParam String nume, @RequestParam String prenume) {
        return clientService.findByNumePrenume(nume,prenume);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/logIn")
    public ClientDTO logIn(@RequestParam String nume, @RequestParam String prenume
            ,@RequestParam String pass) {
        return clientService.logIn(nume,prenume,pass);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/findByNumePrenumePass")
    public ClientDTO findByNumePrenumeReqParam(@RequestParam String nume, @RequestParam String prenume
            ,@RequestParam String pass) {
        return clientService.findByNumePrenumePass(nume,prenume,pass);
    }

    @GetMapping("/findAllClients")
    public List<ClientDTO> findAllParam() {
        return clientService.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/updateClient")
    public ClientDTO updateClient(@RequestParam String email)
    {
        return clientService.updateClient(email);
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/addClient")
    public ClientDTO addClient(@RequestParam String nume,@RequestParam String prenume,
                               @RequestParam String pass,@RequestParam String email)
    {
        return clientService.addClient(nume,prenume,pass,email);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/logOut")
    public ClientDTO logOut(@RequestParam String nume,@RequestParam String prenume,
                            @RequestParam String email)
    {
        return clientService.logOut(nume,prenume,email);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/findLoggedClients")
    public List<ClientDTO> loggedClient()
    {
        return clientService.loggedClients();
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/findAll")
    public List<ClientDTO> findAllClients()
    {
        return clientService.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(value = "/data", produces = MediaType.APPLICATION_XML_VALUE)
    public List<ClientDTO> getXmlData() {
        return clientService.findAll();
    }
}
