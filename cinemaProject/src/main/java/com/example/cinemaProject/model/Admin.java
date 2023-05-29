package com.example.cinemaProject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@Entity
public class Admin  extends User{



    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn
    private List<Movie> movies;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="admin_cu_loc_sa")
    private LocatieFizica locatieSubAdministratie;

    public Admin(Long id, String nume, String prenume,String pass,String email, int age) {
        super(id, nume, prenume,pass,email, age);
    }

    public Admin() {
        super();
    }
}
