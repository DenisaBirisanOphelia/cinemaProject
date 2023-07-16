package com.example.cinemaProject.service;

import com.example.cinemaProject.DTO.AdminDTO;
import com.example.cinemaProject.DTO.ClientDTO;
import org.springframework.stereotype.Component;

@Component
public interface AdminService {
    AdminDTO findByNumePrenumeParola(String nume, String prenume, String password);
}
