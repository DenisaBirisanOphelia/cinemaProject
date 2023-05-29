package com.example.cinemaProject.service;

import com.example.cinemaProject.model.Bilet;
import com.example.cinemaProject.model.Client;
import com.example.cinemaProject.model.Movie;
import com.example.cinemaProject.repository.ClientRepository;
import com.example.cinemaProject.repository.MovieRepository;
import com.example.cinemaProject.service.Implementare.BiletServiceImplementare;
import com.example.cinemaProject.service.Implementare.ClientServiceImplementare;
import com.example.cinemaProject.service.Implementare.MovieServiceImplementare;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ClientServiceTest {
    private static final String EXISTENT_NUME="Birisan";
    private static final String EXISTENT_PRENUME="Denisa";
    private static final String NONEXISTENT_NUME="Rapunzelescu";
    private static final String NONEXISTENT_PRENUME="Gretel";

    private ClientServiceImplementare clientServiceImplementare;

    @Mock
    private ClientRepository clientRepository;

    private Client client;

    @BeforeEach
    void init()
    {
        initMocks(this);
        client=new Client();
        client.setNume(EXISTENT_NUME);
        client.setPrenume(EXISTENT_PRENUME);
        when(clientRepository.findFirstByNumeAndPrenume(EXISTENT_NUME,
                EXISTENT_PRENUME)).thenReturn( client);
    }

    @Test
    void givenExistentClient_whenfindFirstByNumeAndPrenume_thenFindOne()
    {
        clientServiceImplementare=new ClientServiceImplementare(clientRepository);

        //Client client1=clientServiceImplementare.findByName(EXISTENT_NUME,EXISTENT_PRENUME);

     //   assertNotNull(client1);
       // assertEquals(client1.getAge(),client.getAge());
    }


    @Test
    void givenNonExistentClient_whenfindFirstByNumeAndPrenume_thenThrowException()
    {
        when(clientRepository.findFirstByNumeAndPrenume(NONEXISTENT_NUME,NONEXISTENT_PRENUME))
                .thenReturn(null);

       // Exception excp=assertThrows(NullPointerException.class,()->
     //   {
      //      clientServiceImplementare.findByName(NONEXISTENT_NUME,NONEXISTENT_PRENUME);
      //  });
    }
    @Test
    void deleteClientExistent()
    {   client=new Client();
        client.setNume(EXISTENT_NUME);
        client.setPrenume(EXISTENT_PRENUME);
        clientServiceImplementare=new ClientServiceImplementare(clientRepository);

        clientServiceImplementare.deleteClient(client.getNume(),client.getPrenume());
        verify(clientRepository).delete(client);
    }
    @Test
    void updateClientAge()
    {   client=new Client();
        client.setNume(EXISTENT_NUME);
        client.setPrenume(EXISTENT_PRENUME);

        clientServiceImplementare=new ClientServiceImplementare(clientRepository);

       // Client client2=clientServiceImplementare.updateClient(client,70);
        //assertNotNull(client2);
       // assertEquals(client2.getAge(),70);

    }

}
