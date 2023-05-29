package com.example.cinemaProject.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class WatchList {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String numeWatchList;

    @OneToOne(mappedBy = "listaVizionate",fetch = FetchType.EAGER)
    @JsonManagedReference
    private Client client;

    @OneToMany(mappedBy = "watchList",fetch = FetchType.EAGER,orphanRemoval = true,cascade = CascadeType.REMOVE)
    private Set<Movie> listaFilmeDeVazut;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WatchList watchList = (WatchList) o;
        return  Objects.equals(numeWatchList, watchList.numeWatchList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeWatchList);
    }
}