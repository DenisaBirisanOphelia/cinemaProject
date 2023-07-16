package com.example.cinemaProject.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Departament {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToMany(mappedBy="departamente")
    private List<LocatieFizica> locatiiFizice;

    @OneToMany(mappedBy = "departament")
    private List<Angajat> angajati;
}
