package com.example.cinemaProject.controller;

import com.example.cinemaProject.DTO.BiletDTO;
import com.example.cinemaProject.DTO.CosCumparaturiDTO;
import com.example.cinemaProject.service.BiletService;
import com.example.cinemaProject.service.CosCumparaturiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cosCumparaturi")
public class CosCumparaturiController {
    @Autowired
    private CosCumparaturiService cosCumparaturiService;


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/all")
    public List<CosCumparaturiDTO> getAll()
    {
        return cosCumparaturiService.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getAllBilete")
    public List<BiletDTO> getAllBilete()
    {
        return cosCumparaturiService.getAllBilete();
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/addItemToCos")
    public CosCumparaturiDTO addItemToCosReqParam(@RequestParam Long id,@RequestParam String nume,@RequestParam
                                                  String prenume) {
      //pt clientul dat, il caut in lista de clienti si ii adaug lui biletul cu id ul dat
        return cosCumparaturiService.addBiletToCosCumparaturi(id,nume,prenume);
    }

    //las aici metoda, dar defapt, cd sterg un bilet din tabelul Bilet, se sterge automat si din cos
    //cosul e  o abstractizare, dar defapt, as putea la fel de bine sa iau datele necesare pt fe
    //si din direct din tabelul Bilet
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/removeItemFromCos")
    public CosCumparaturiDTO removeItemFromCosReqParam(@RequestParam String numeFilm,
                                                       @RequestParam String regizor,@RequestParam String locInSala,@RequestParam String nume,@RequestParam
                                                               String prenume) {

        return cosCumparaturiService.removeBiletFromCosCumparaturi(numeFilm,regizor,locInSala,nume,prenume);
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/removeAllFromCos")
    public List<CosCumparaturiDTO> removeAllFrromCos(@RequestParam String nume,@RequestParam String prenume) {

       return   cosCumparaturiService.deleteAllFromCosCumparaturi(nume,prenume);
    }

}
