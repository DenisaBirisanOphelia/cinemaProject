package com.example.cinemaProject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class CosCumparaturi {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int pretTotal;

    @OneToMany(mappedBy = "cosCumparaturi",fetch=FetchType.EAGER)
    private List<Bilet> bileteDinCosCumparaturi;

    //cu tipul asta de cascades,  cand sterg cvdin cosul de cumparaturi, nu se propaga stergerea
    //si in client ca asta era bug u din front end
    @ManyToOne(fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private Client client;

    public int getPretTotal() {
        return pretTotal;
    }

    public void setPretTotal(int pretTotal) {
        this.pretTotal = pretTotal;
    }


}
