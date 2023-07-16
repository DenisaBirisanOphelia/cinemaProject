package com.example.cinemaProject.service.Implementare;

import com.example.cinemaProject.DTO.ClientDTO;
import com.example.cinemaProject.mapper.ClientMapper;
import com.example.cinemaProject.model.Client;
import com.example.cinemaProject.model.Movie;
import com.example.cinemaProject.model.User;
import com.example.cinemaProject.repository.ClientRepository;
import com.example.cinemaProject.service.ClientService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;





@Transactional
@Service
public class ClientServiceImplementare implements ClientService {
    @Autowired
    private ClientRepository clientRepository;

   // @Autowired
    //private PasswordEncoder passwordEncoder;

    public static String encryptPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return bytesToHexString(encodedHash);
        } catch (NoSuchAlgorithmException e) {
            // Handle the exception appropriately
            return null;
        }
    }

    private static String bytesToHexString(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static boolean verifyPassword(String password, String encodedPassword) {
        String hashedPassword = encryptPassword(password);
        return hashedPassword != null && hashedPassword.equals(encodedPassword);
    }
    public ClientServiceImplementare(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }



    @Override
    public ClientDTO addClient(String nume, String prenume, String pass, String email) {
        Client client = new Client();
        if(nume!=null && prenume!=null & email!=null &pass!=null) {
            client.setNume(nume);
            client.setPrenume(prenume);
            client.setEmail(email);

            //am encriptat parola pt client
           String encryptedPass=encryptPassword(pass);
            client.setPassword(encryptedPass);
            //initial e creat ca fiind neconfirmat
            client.setConfirmedClient(true);
            return ClientMapper.mapModelToDto(clientRepository.save(client));
        }
        return null;
    }

    @Override
    public ClientDTO logIn(String nume, String prenume, String password) {
       // Client c=clientRepository.
              //  findFirstByNumeAndPrenumeAndPassword(nume, prenume, password);
        Client c=clientRepository.findFirstByNumeAndPrenume(nume,prenume);

        if (c==null) return null;
      ;
        System.out.println(password);
        System.out.println(c.getPassword());
        System.out.println( verifyPassword(password,c.getPassword()));
       if (c.getConfirmedClient() &&  verifyPassword(password,c.getPassword())) {//obligatoriu sa fie confirmat cand fac logIn u
            c.setLoggedClient(true);
            clientRepository.save(c);
            return ClientMapper.mapModelToDto(c);
        }
         return null;
    }

    @Override
    public ClientDTO findByNumePrenume(String nume, String prenume) {
        Client c = clientRepository.
                findFirstByNumeAndPrenume(nume, prenume);
        if(c==null) return null;
        else {
            if (c.getConfirmedClient()) return ClientMapper.mapModelToDto(c);
            else return null;
        }
    }

    @Override
    public ClientDTO findByNameOnly(String nume) {
        return ClientMapper.mapModelToDto(clientRepository.findFirstByNume(nume));
    }

    @Override
    public ClientDTO updateClient(String email) {
        Client c = clientRepository.findFirstByEmail(email);

        if(c!=null) {
            c.setConfirmedClient(true);
            clientRepository.save(c);
            return ClientMapper.mapModelToDto(c);
        }
        else return null;
    }

    @Override
    public Client updateClientAddMovieToWatchList(Client client, Movie film) {
        //nuj daca e ok asa cu present
       /* if(clientRepository.findById(client.getId()).isPresent())
        {
            //scot watchlistu existent din client
            WatchList m= new WatchList();
           // if(client.getListaVizionate()!=null) {
           //     m = client.getListaVizionate();
          //  }
            //din obiectu de tip watchlist scot lista de filme
            Set<Movie> lista=new HashSet<Movie>();
            if(m.getListaFilmeDeVazut()!=null) {
                 lista = m.getListaFilmeDeVazut();
            }
            lista.add(film);
            //i adaug filmu vrut de mn
            m.setListaFilmeDeVazut(lista);
            //il pun in ob de tip Watchlist si updatez clientu meu plus propag in repo
           // client.setListaVizionate(m);
           // client.getListaVizionate().getListaFilmeDeVazut().stream().forEach(movie->
             //       System.out.println(movie.getName()));
            clientRepository.save(client);
            return client;
        }*/
        return null;
    }

    @Override
    public void updateClientAddMovieToListaMoviesCurente(Client client, Movie film) {
        //cred ca sterg iara lista ant de filme, o modific si o pun pe cea noua
        //sa nu am duplicate

        Client client1 = clientRepository.findFirstByNumeAndPrenume(client.getNume(), client.getPrenume());

        if(client1!=null) {
            List<Movie> lista = new ArrayList<>();
            lista.addAll(client1.getMovies());
            lista.add(film);
            client1.setMovies(lista);
            clientRepository.save(client1);
        }

    }

    @Override
    public void deleteClient(String nume, String prenume) {
        clientRepository.delete(clientRepository.findFirstByNumeAndPrenume(nume, prenume));
    }

    @Override
    public List<ClientDTO> findAll() {
        List<ClientDTO> lista = new ArrayList<>();
        for (Client client : clientRepository.findAll()) {
            lista.add(ClientMapper.mapModelToDto(client));
        }
        return lista;
    }

    @Override
    public ClientDTO logOut(String nume, String prenume, String email) {
        Client c = clientRepository.findFirstByNumeAndPrenumeAndEmail(nume, prenume, email);
        if(c==null) return null;
        c.setLoggedClient(false);
        clientRepository.save(c);
        return ClientMapper.mapModelToDto(c);
    }

    @Override
    public List<ClientDTO> loggedClients() {
        List<Client> c=clientRepository.findAllByLoggedClient(true);
        List<ClientDTO> lista=new ArrayList<>();
        for(Client k:c)
        {
            lista.add(ClientMapper.mapModelToDto(k));
        }
        return lista;
    }

    @Override
    public ClientDTO findByNumePrenumePass(String nume, String prenume, String pass) {
        Client c = clientRepository.
                findFirstByNumeAndPrenumeAndPassword(nume, prenume,pass);
        if(c==null) return null;
        else {
            if (c.getConfirmedClient()) return ClientMapper.mapModelToDto(c);
            else return null;
        }
    }



}
