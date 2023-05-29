package com.example.cinemaProject.controller;

import com.example.cinemaProject.DTO.AdminDTO;
import com.example.cinemaProject.DTO.ClientDTO;
import com.example.cinemaProject.service.AdminService;
import com.example.cinemaProject.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/findNumePrenumePass")
    public AdminDTO findByNumePrenumeReqParam(@RequestParam String nume, @RequestParam String prenume
            , @RequestParam String pass) {
        return adminService.findByNumePrenumeParola(nume,prenume,pass);
    }
}
