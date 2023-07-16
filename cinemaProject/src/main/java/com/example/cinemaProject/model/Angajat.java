package com.example.cinemaProject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Angajat {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String nume;
    private String prenume;
    private int varsta;

    @ManyToOne
    @JoinColumn(name="fk_angajati_departament")
    private Departament departament;


}
