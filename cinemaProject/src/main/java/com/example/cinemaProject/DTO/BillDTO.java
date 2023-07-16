package com.example.cinemaProject.DTO;

import jakarta.persistence.Column;

public class BillDTO {
    private Integer id;
    private String uuid;
    private String name;
    private String prenume;
    private String email;
    private String paymentMethod;
    private Integer total;
    private String productDetails;
    private String createdBy;
}
