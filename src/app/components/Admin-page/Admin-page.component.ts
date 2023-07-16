import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Client} from "../../model/Client";
import {ClientService} from "../../service/ClientService";
import {ActivatedRoute, Router} from "@angular/router";
import {AdminService} from "../../service/AdminService";
import {Movie, MovieKeys} from "../../model/Movie";
import {MovieService} from "../../service/MovieService";
import {MatDialog} from "@angular/material/dialog";
import {AccountDialogComponent} from "../AccountDialog/AccountDialog.component";

import { xml2js, js2xml } from 'xml-js';
import { saveAs } from 'file-saver';

@Component({
  selector: 'app-first-page',
  templateUrl: './Admin-page.component.html',
  styleUrls: ['./Admin-page.component.css']
})
export class AdminPageComponent implements OnInit {

 movieInstance = new Movie(undefined, undefined, undefined,undefined,undefined,
   undefined,undefined);
  filterMainData: MovieKeys[] = (Object.keys(this.movieInstance) as MovieKeys[]).slice(1)
  movieList: Movie[] = [];

  updateForm: FormGroup | undefined;
  nume:string="";
  updateOption:string="";
  newValue:string="";

  deleteForm:FormGroup|undefined;
  deleteOption:string="";
  deleteField:string="";

  insertForm:FormGroup|undefined;
  numeFilm:string="";
  regizor:string="";
  rating:number=0;
  clickNr:number=0;
  clientList: Client[]=[];

  constructor(private movieService: MovieService,
              private formBuilder:FormBuilder,
              private route:ActivatedRoute,
              public router: Router,
              private dialog: MatDialog,
              private clientService:ClientService) { }

    ngOnInit(): void {
      this.initForms();
      this.heya();
     }
     heya()
     {
       this.movieService.getAllMovies().subscribe((res) => {
           console.log(res);
           this.movieList = res;
         },
         (_error) => {

         });

       this.clientService.getAllClients().subscribe((res1:Client[]) => {
           console.log(res1);
           this.clientList = res1;
         },
         (_error) => {

         });
     }
    initForms() {
    this.updateForm = this.formBuilder.group({
      nume: ['default', Validators.required],
      updateOption: ['default', Validators.required],
      newValue: ['default', Validators.required],
    })
      this.deleteForm = this.formBuilder.group({
        deleteOption: ['default', Validators.required],
        deleteField: ['default', Validators.required],
      })

      this.insertForm = this.formBuilder.group({
        numeFilm: ['default', Validators.required],
        regizor: ['default', Validators.required],
        rating: ['default', Validators.required],
      })
  }

  update() {
    console.log(this.nume);
    console.log(this.updateOption);
    console.log(this.newValue)
    if (isNaN(Number(this.newValue)))
    {
    this.movieService.updateMovie(this.nume,this.updateOption,this.newValue).subscribe((res) => {
        console.log(res);
        this.heya();
      },
      (_error) => {

      });
  }
    else {
      this.movieService.updateMovie2(this.nume,this.updateOption,parseInt(this.newValue)).subscribe((res) => {
          console.log(res);
          this.heya();
        },
        (_error) => {

        });

    }
  }

  delete() {

    if (isNaN(Number(this.deleteField)))
    {
      this.movieService.deleteMovie(this.deleteOption,this.deleteField).subscribe((res) => {
          console.log(res);
          this.heya();
        },
        (_error) => {

        });
    }
    else {
      this.movieService.deleteMovie2(this.deleteOption,parseInt(this.deleteField)).subscribe((res) => {
          console.log(res);
          this.heya();
        },
        (_error) => {

        });

    }
  }
  add()
  {
    this.movieService.insert(this.numeFilm,this.regizor,this.rating).subscribe((res) => {
        console.log(res);
        this.heya();
      },
      (_error) => {

      });

  }
  openAccountDialog()
  {
    this.dialog.open(AccountDialogComponent)
  }
  ngAfterViewInit()
  {
    const buttonElement=document.getElementById("status");

    if(buttonElement!=null)
      buttonElement.addEventListener('click',()=>
      {
        const divSearchBox=document.getElementById("statusBox")
        if(divSearchBox!=null) {
          if (this.clickNr % 2 == 0) {
            divSearchBox.style.visibility = 'visible'
          }
          else
            divSearchBox.style.visibility = 'hidden'
          this.clickNr++;
        }
      })
  }


  isLogged(loggedClient: any):string {
    if (loggedClient==true) return "Logged in .........";
    else return "NOT logged........";
  }

  showXML() {
    var xmlH = '<?xml version="1.0" encoding="UTF-8"?>\n';

 let json: any;
  this.clientService.getAllClients().subscribe((rez)=>
  {
     var json=rez;
    const xml = js2xml(json, { compact: true, ignoreComment: true, spaces: 4 });
    const xmlFinal=xmlH+xml;
    console.log(xmlFinal);

    const blob = new Blob([xmlFinal], { type: 'application/xml' });
    const url = URL.createObjectURL(blob);
    const link = document.createElement('a');
    link.href = url;
    link.download = 'output.xml'; // Set the file name with .xml extension
    link.click();

  },
    (_error)=>
    {

    })



  }
}
