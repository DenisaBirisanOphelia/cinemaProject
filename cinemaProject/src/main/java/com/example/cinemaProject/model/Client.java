package com.example.cinemaProject.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Client extends User {
    private Boolean confirmedClient=false;

    private Boolean loggedClient=false;


    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn
    private List<Movie> movies;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="fk_client_to_bilet")
    private List<CosCumparaturi> cosCumparaturi;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    @JoinColumn(name="client_cu_lista_vizionate")
    private WatchList listaVizionate;

    @OneToMany(mappedBy = "clientulCeADatReview",cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    private List<Review> listaReviews;

    public Client(Long id, String nume, String prenume,String password, int age,String email, List<Movie> movies) {
        super(id, nume, prenume,password,email, age);
        this.movies = new ArrayList<Movie>();
        //this.listaVizionate = new WatchList();
    }
}
