package com.example.cinemaProject.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ClientDTO {
   private Long id;
   private String nume;
    private String prenume;
    private String password;
    private String email;
    private int age;
    private Boolean confirmedClient;
    private Boolean loggedClient;
}
