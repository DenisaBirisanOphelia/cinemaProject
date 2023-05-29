package com.example.cinemaProject.service.Implementare;

import com.example.cinemaProject.model.Bilet;
import com.example.cinemaProject.model.Bill;
import com.example.cinemaProject.repository.BiletRepository;
import com.example.cinemaProject.service.BillService;
import com.example.cinemaProject.service.CosCumparaturiService;
import com.google.common.base.Strings;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.json.*;
import com.example.cinemaProject.model.Bilet;

@Slf4j
@Service
public class BillServiceImpl implements BillService {




    @Autowired
    private BiletRepository biletRepository;


    @Override
    public String generateReport(String nume,String prenume,String email,String adresa,String
                                 city,String state) throws IOException {
        StringBuilder str = new StringBuilder();

        BufferedWriter scriitor = new BufferedWriter(new FileWriter("Factura"));
        scriitor.write("FACTURA PENTRU ACHIZITONARE FILME \n");
        scriitor.write("Clientul: "+nume+" "+prenume+"\n");
        scriitor.write("Email: "+email+"\n");
        scriitor.write("Adresa: "+ adresa+" "+city+" "+state+" \n");
        scriitor.write("PRODUSE ACHIZIONATE: \n");

        int S=0;
        for(Bilet b:biletRepository.findAll())
        {
        scriitor.write("Biletul cu locul: "+b.getLocInSala()+" la filmul "+
                b.getFilmDinBilet().getName()+", in valoare de  "+b.getPret()+" $$$ \n");
        S+=b.getPret();
        }

        scriitor.write("Total: "+ S+" $$$");
        scriitor.write("Multumim ca ati decis sa ne fiti client!");
        scriitor.close();
        return "ceva";

    }
}