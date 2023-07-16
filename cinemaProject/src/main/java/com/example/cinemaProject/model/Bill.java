package com.example.cinemaProject.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;

//astea pt ca folosesc DAO
@NamedQuery(name = "Bill.getAllBills", query = "select b from Bill b order by b.id desc")
@NamedQuery(name = "Bill.getByUserName", query = "select b from Bill b where b.createdBy =: username order by b.id desc")

    @Data
//constructor
    @Entity//creaza tabelu
    @DynamicInsert
    @DynamicUpdate
    @Table(name = "bill")
    public class Bill {

        private static final long serialVersionUID=1L;

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)//autogenerat pentru fiecare noua valoare
        @Column(name = "id")
        private Integer id;

        @Column(name = "uuid") //al facturii
        private String uuid;

        @Column(name = "name")
        private String name;

         @Column(name = "prenume")
         private String prenume;

        @Column(name = "email")
        private String email;

        @Column(name = "payment_method")
        private String paymentMethod;

        @Column(name = "total")
        private Integer total;

        @Column(name = "productdetails", columnDefinition = "json") //asta ii ca sa avem datele complete de la fiecare produs din cos
        private String productDetails;

        @Column(name = "createdby")
        private String createdBy;
    }
