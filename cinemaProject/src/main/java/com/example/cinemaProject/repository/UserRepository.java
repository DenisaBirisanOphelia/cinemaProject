package com.example.cinemaProject.repository;

import com.example.cinemaProject.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User,Long> {
    List<User> findAllByAge(int nr);
}
