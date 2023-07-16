package com.example.cinemaProject.controller;


import com.example.cinemaProject.DTO.ClientDTO;
import com.example.cinemaProject.DTO.MovieDTO;
import com.example.cinemaProject.DTO.ReviewDTO;
import com.example.cinemaProject.service.ClientService;
import com.example.cinemaProject.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/addReview")
    public ReviewDTO addReview(@RequestParam String nume, @RequestParam String regizor,
                               @RequestParam int stele, @RequestParam String comentariu,
                               @RequestParam String numeClient,@RequestParam String
                               prenumeClient) {
        return reviewService.addReview(nume,regizor,stele,comentariu,numeClient,prenumeClient);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getAllReviews")
    public List<ReviewDTO> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getMovieFromReview")
    public MovieDTO getMovieFromReview(@RequestParam Long id) {
        return reviewService.getMovieFromReview(id);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/getClientFromReview")
    public ClientDTO getClientFromReview(@RequestParam Long id) {
        return reviewService.getClientFromReview(id);
    }

}
