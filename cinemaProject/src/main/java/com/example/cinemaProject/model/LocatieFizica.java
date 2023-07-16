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
//un cinema are mai multe locatii fizice
public class LocatieFizica {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
   private String oras;
   private String strada;
   private int nr;

   @OneToOne(mappedBy="locatieSubAdministratie")
   private Admin adminLocatie;

   @ManyToMany
   @JoinColumn
   private List<Departament> departamente;

}
