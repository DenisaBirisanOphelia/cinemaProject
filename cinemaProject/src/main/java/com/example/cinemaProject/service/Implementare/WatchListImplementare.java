package com.example.cinemaProject.service.Implementare;

import com.example.cinemaProject.DTO.MovieDTO;
import com.example.cinemaProject.DTO.WatchListDTO;
import com.example.cinemaProject.mapper.MovieMapper;
import com.example.cinemaProject.mapper.WatchListMapper;
import com.example.cinemaProject.model.Client;
import com.example.cinemaProject.model.Movie;
import com.example.cinemaProject.model.WatchList;
import com.example.cinemaProject.repository.ClientRepository;
import com.example.cinemaProject.repository.MovieRepository;
import com.example.cinemaProject.repository.WatchListRepository;
import com.example.cinemaProject.service.WatchListService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Transactional
public class WatchListImplementare implements WatchListService {
    @Autowired
    private WatchListRepository watchListRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ClientRepository clientRepository;



    public WatchListImplementare(WatchListRepository watchListRepository) {
        this.watchListRepository = watchListRepository;
    }

//    @Override
//    public WatchList saveWatchList(WatchList watchList) {
//        //aici trebuie sa fac merge la filme cand mai exista alt watchList pt clientul resp
//        //trebe sa verific daca am asignat deja sau nu un client pt watchlistu respectiv
//        //cam stupid, da asa fac io cod aparent
//        if(watchList.getClient()!=null) {
//            WatchList watchList1 = watchListRepository.findWatchListByClient(watchList.getClient());
//            if (watchList1 != null) {
//                //fac merge la lista de filme
//                Set<Movie> listaMovies = new HashSet<Movie>();
//                for (Movie m : watchList.getListaFilmeDeVazut())
//                    listaMovies.add(m);
//                for (Movie m : watchListRepository.findWatchListByClient(watchList.getClient()).getListaFilmeDeVazut())
//                    listaMovies.add(m);
//                watchList1.setListaFilmeDeVazut(listaMovies);
//                watchListRepository.save(watchList1);
//                return watchList1;
//            }
//            else
//            {
//                watchListRepository.save(watchList);
//                return watchList;
//            }
//        }
//       else //salvez pur si simplu
//        {
//            watchListRepository.save(watchList);
//            return watchList;
//        }
//    }
//
    @Override
    public WatchListDTO saveMovieInWatchListForClient(String nume,String regizor,String nume1,
            String prenume) {
        //aici fac validarea sa nu poata sa fie adaugat in watchlist un film ce nu e in
        //SAU O FACE AUTOMAT DIN CAUZA RELATIILOR????????
        //tabelul de movies

          Movie m=movieRepository.findFirstByNameAndRegizor(nume,regizor);
          if(m==null) return null;

          Client c=clientRepository.findFirstByNumeAndPrenume(nume1,prenume);

           WatchList watchList=watchListRepository.findWatchListByClient(c);
           if(watchList==null) watchList=new WatchList();

               Set<Movie> noua = watchList.getListaFilmeDeVazut();
               if(noua==null)  noua = new HashSet<Movie>();
               noua.add(m);
               watchList.setListaFilmeDeVazut(noua);
               watchListRepository.save(watchList);
               m.setWatchList(watchList);
               movieRepository.save(m);

            for (Movie mySet : watchList.getListaFilmeDeVazut()) {
            System.out.println(mySet.getName());
        }
               c.setListaVizionate(watchList);
               clientRepository.save(c);
               //noua.stream().forEach(film1-> System.out.println(film1.getName()));
               return WatchListMapper.mapModelToDto(watchList);
    }

    @Override
    public List<MovieDTO> getMoviesForClient(String nume, String prenume) {
        Client c=clientRepository.findFirstByNumeAndPrenume(nume,prenume);
        if(c==null) return null;
        WatchList watchList=watchListRepository.findWatchListByClient(c);
        if(watchList==null) return null;
        List<MovieDTO> lista=new ArrayList<>();
        for(Movie m:watchList.getListaFilmeDeVazut())
        {
            lista.add(MovieMapper.mapModelToDto(m));
        }
        return lista;
    }

    @Override
    public void deleteMovieFromWatchList(String nume, String regizor, String nume1, String prenume) {
        Movie m=movieRepository.findFirstByNameAndRegizor(nume,regizor);
        if(m==null) return;
        Client c=clientRepository.findFirstByNumeAndPrenume(nume1,prenume);
        if(c==null) return;
        WatchList watchList=watchListRepository.findWatchListByClient(c);
        if(watchList==null) return;
        Set<Movie> lista=watchList.getListaFilmeDeVazut();
        if(lista==null) return;
        lista.remove(m);
        watchList.setListaFilmeDeVazut(lista);
        m.setWatchList(null);
        movieRepository.save(m);
        watchListRepository.save(watchList);

    }

    @Override
    public List<WatchList> findByClient(Client client) {
        List<WatchList> lista=new ArrayList<>();
         watchListRepository.findAll().forEach(w->
        { //practic ma duc prin toate filmele si cd gasesc unul care
            w.getListaFilmeDeVazut().forEach(movie->
            {
                movie.getClienti().forEach(client1->
                {
                    if (client1.getId()==client.getId())
                        lista.add(w);
                });
            });
        });
         return lista;
    }

    @Override
    public Set<WatchList> findWatchListContainingMovie(Movie film) {
        Set<WatchList> lista=new HashSet<>();
        watchListRepository.findAll().forEach(w->
        {
           // System.out.println(w.getNumeWatchList());

                w.getListaFilmeDeVazut().stream().forEach(movie->
                {
                    if (movie.getName().equals(film.getName())) {

                         lista.add(w);
                    }
                });

        });
        return lista;
    }

    @Override
    public WatchList updateWatchList(WatchList watchList, Movie filmDeInlocuit, Movie filmCuCareSeInlocuieste) {
        if(watchList.getListaFilmeDeVazut().contains(filmDeInlocuit))
        {
            Set<Movie> lista=watchList.getListaFilmeDeVazut();
            lista.remove(filmDeInlocuit);
            lista.add(filmCuCareSeInlocuieste);
            watchList.setListaFilmeDeVazut(lista);
            //asasalvez si in repo,ff imp!!
            watchListRepository.save(watchList);
            return watchList;
        }
        else return null;
    }

    @Override
    public void deleteWatchList(Client client) {
        List<WatchList> watchList=this.findByClient(client);
        if (watchList!=null)
        {
            watchListRepository.deleteAll(watchList);
        }

    }




}
