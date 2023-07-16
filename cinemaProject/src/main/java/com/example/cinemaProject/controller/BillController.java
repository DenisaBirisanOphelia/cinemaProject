package com.example.cinemaProject.controller;

import com.example.cinemaProject.service.BiletService;
import com.example.cinemaProject.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping(path = "/bill")
public class BillController {
    @Autowired
    private BillService billService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/createBill")
    public String generateReport(@RequestParam  String nume,
                                 @RequestParam String prenume,
                                 @RequestParam String email,
                                 @RequestParam String adresa,
                                 @RequestParam String city,
                                 @RequestParam String state) {
        try {
            return billService.generateReport(nume,prenume,email,adresa,city,state);
        } catch (Exception e) {
            e.printStackTrace();
        }
       return null;
    }
}
