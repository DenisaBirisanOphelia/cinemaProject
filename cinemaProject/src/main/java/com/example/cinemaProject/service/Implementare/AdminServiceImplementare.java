package com.example.cinemaProject.service.Implementare;

import com.example.cinemaProject.DTO.AdminDTO;
import com.example.cinemaProject.mapper.AdminMapper;
import com.example.cinemaProject.repository.AdminRepository;
import com.example.cinemaProject.service.AdminService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class AdminServiceImplementare  implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public AdminServiceImplementare(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }


    @Override
    public AdminDTO findByNumePrenumeParola(String nume, String prenume, String password) {
        AdminDTO a= AdminMapper.mapModelToDto(adminRepository.
                findFirstByNumeAndPrenumeAndPassword(nume,prenume,password));
       return a;
    }
}
