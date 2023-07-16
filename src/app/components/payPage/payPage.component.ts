import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Client} from "../../model/Client";
import {Movie,MovieKeys} from "../../model/Movie";
import {MovieService} from "../../service/MovieService";
import {catchError, map, Observable, of} from "rxjs";
import {ActivatedRoute, Route, Router} from "@angular/router";
import {BillService} from "../../service/BillService";
import {error} from "@angular/compiler-cli/src/transformers/util";



@Component({
  selector: 'app-first-page',
  templateUrl: './payPage.component.html',
  styleUrls: ['./payPage.component.css']
})
export class PayPageComponent implements OnInit {

  constructor(private movieService: MovieService,
              private formBuilder: FormBuilder,
              public router: Router,
              private billService:BillService) {
    this.siteKey="6Ld9BuAlAAAAAHn_6kXn0azYF4DuITpZsTQO09mB";
  }


  siteKey:string;
  payForm:FormGroup | undefined;

  nume:string="";
  prenume:string="";
  email:string="";
  adresa:string="";
  city:string="";
  state:string="";

  ngOnInit(): void {

    this.payForm=this.formBuilder.group({
      nume:[null, Validators.required],
      email:[null,Validators.required],
      adresa:[null,[Validators.required]],
      city:[null,[Validators.required]],
      state:[null,[Validators.required]],
    })
  }


  createBill() {
    const input = this.payForm?.value.nume;
    const regex = /(\S+)\s+(\S+)/;

    const matches = input.match(regex);
    var word1="";
    var word2="";
    if (matches) {
       word1 = matches[1];
       word2 = matches[2];

      console.log(word1); // Output: "Hello"
      console.log(word2); // Output: "World"
    } else {
      console.log("No matches found.");
    }


    this.billService.createBill(word1,word2,this.payForm?.value.email,
      this.payForm?.value.adresa,
      this.payForm?.value.city,this.payForm?.value.state).subscribe((res)=>
    {},
      (_error)=>
      {
  })
  }
}

