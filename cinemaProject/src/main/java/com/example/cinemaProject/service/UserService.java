package com.example.cinemaProject.service;

import com.example.cinemaProject.model.Review;
import com.example.cinemaProject.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {
    User saveUser(User user);
    List<User> findByAge(int nr);
    User updateUser(User user,int newAge);
    void deleteUser(User user);

}
