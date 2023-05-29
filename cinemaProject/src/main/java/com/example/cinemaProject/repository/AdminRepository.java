package com.example.cinemaProject.repository;

import com.example.cinemaProject.model.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends CrudRepository<Admin,Long> {
    Admin findFirstByNumeAndPrenumeAndPassword(String nume, String prenume, String password);
}
