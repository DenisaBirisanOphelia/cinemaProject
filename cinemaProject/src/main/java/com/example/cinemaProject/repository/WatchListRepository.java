package com.example.cinemaProject.repository;

import com.example.cinemaProject.model.Client;
import com.example.cinemaProject.model.WatchList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WatchListRepository extends CrudRepository<WatchList,Long> {

    WatchList findWatchListByClient(Client client);
}
