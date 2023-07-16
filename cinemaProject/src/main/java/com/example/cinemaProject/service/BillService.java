package com.example.cinemaProject.service;

import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Map;

public interface BillService {
    public String generateReport(String nume,String prenume,String email,String adresa,String
            city,String state) throws IOException;

}