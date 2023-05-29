package com.example.cinemaProject.service.Implementare;

import com.example.cinemaProject.DTO.BiletDTO;
import com.example.cinemaProject.DTO.CosCumparaturiDTO;
import com.example.cinemaProject.mapper.BiletMapper;
import com.example.cinemaProject.mapper.CosCumparaturiMapper;
import com.example.cinemaProject.model.Bilet;
import com.example.cinemaProject.model.Client;
import com.example.cinemaProject.model.CosCumparaturi;
import com.example.cinemaProject.model.Movie;
import com.example.cinemaProject.repository.BiletRepository;
import com.example.cinemaProject.repository.ClientRepository;
import com.example.cinemaProject.repository.CosCumparaturiRepository;
import com.example.cinemaProject.repository.MovieRepository;
import com.example.cinemaProject.service.CosCumparaturiService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CosCumparaturiServiceImplementare implements CosCumparaturiService {
    @Autowired
    private CosCumparaturiRepository cosCumparaturiRepository;

    //stiu ca nu mai respecta SRP sau ISP, dar nuj cum sa fac altfel
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private BiletRepository biletRepository;

    @Autowired
    private MovieRepository movieRepository;

    public CosCumparaturiServiceImplementare(CosCumparaturiRepository cosCumparaturiRepository,
                                             ClientRepository clientRepository,
                                             BiletRepository biletRepository) {
        this.cosCumparaturiRepository = cosCumparaturiRepository;
        this.clientRepository = clientRepository;
        this.biletRepository = biletRepository;
        this.movieRepository=movieRepository;
    }

    @Override
    public CosCumparaturiDTO addBiletToCosCumparaturi(Long id, String nume, String prenume) {

          Client c=clientRepository.findFirstByNumeAndPrenume(nume,prenume);
          if(c!=null) //desi nu voi intra niciodata pe cond asta, pot ca iau date din fe din
              //sesiunea curenta,deci mereu am un user
          {
              Bilet b=biletRepository.findFirstById(id);
              if(b!=null)
              {
                  CosCumparaturi cos=new CosCumparaturi();//creez o noua intrare
                  List<Bilet>  list=new ArrayList<>();
                  list.add(b);//adaug biletul
                  cos.setBileteDinCosCumparaturi(list);
                  //ii pun pretul ca ala conteaza
                  cos.setPretTotal(b.getPret());
                  //setez si fk pt bilet
                  b.setCosCumparaturi(cos);
                  System.out.println(c.getNume());
                  cos.setClient(c);//il leg de client
                 cosCumparaturiRepository.save(cos);//il salvez in db
                  //daca salvam inainte biletul, imi crea automat o intrare de fk pt el cu clientul null
                  //si apoi pusca la delete cu stack overflow de la null pointer excp
                  biletRepository.save(b);
               return CosCumparaturiMapper.mapModelToDto(cos);
              }
              else
                  return null;

          }
        else {
              System.out.println("nu adauga bine cu clientul");
              return null;
          }

    }

    @Override
    public List<Bilet> findBileteUnderASUm(int sum) {
        List<Bilet> lista=new ArrayList<Bilet>();
        cosCumparaturiRepository.findAll().forEach(cosEntry->
                {
                    cosEntry.getBileteDinCosCumparaturi().forEach(bilet->
                    {
                        if(bilet.getPret()<sum && bilet.getPret()!=0)
                            lista.add(bilet);
                    });
                });
        return lista;
    }

    @Override
    public List<CosCumparaturiDTO> deleteAllFromCosCumparaturi(String nume, String prenume) {
         Client c=clientRepository.findFirstByNumeAndPrenume(nume,prenume);
         if(c==null) {
             System.out.println("a fost din cauza clientului");
             return null;
         }
         List<CosCumparaturi> cosCumparaturi=cosCumparaturiRepository.findAllByClient(c);

         for(CosCumparaturi cos:cosCumparaturi)
         {
             //sterg biletul acela
              Bilet b=biletRepository.findFirstByCosCumparaturi(cos);
             System.out.println(b.getId());
             biletRepository.delete(b);

             //se va sterge automat si din cosul de cumparaturi
         }

        return null;
    }

    @Override
    public CosCumparaturiDTO removeBiletFromCosCumparaturi(String numeFilm, String regizor,String locInSala,
                                                           String nume, String prenume) {
        //caut biletul pt filmul respectiv
        Movie m=movieRepository.findFirstByNameAndRegizor(numeFilm,regizor);
        if(m==null) return null;
        Bilet b=biletRepository.findFirstByFilmDinBiletAndLocInSala(m,locInSala);
       // System.out.println(b.getId());
        if(b==null) return null;
         biletRepository.delete(b);

         return null;
    }

    @Override
    public List<CosCumparaturiDTO> findAll() {
        List<CosCumparaturi> c= (List<CosCumparaturi>) cosCumparaturiRepository.findAll();
        List<CosCumparaturiDTO> list=new ArrayList<>();
        for(CosCumparaturi cos:c)
            list.add(CosCumparaturiMapper.mapModelToDto(cos));
        return list;
    }

    @Override
    public List<BiletDTO> getAllBilete() {
        List<Bilet> b= (List<Bilet>) biletRepository.findAll();
        List<BiletDTO> list=new ArrayList<>();
        for(Bilet be:b)
            list.add(BiletMapper.mapModelToDto(be));
        return list;
    }
}
