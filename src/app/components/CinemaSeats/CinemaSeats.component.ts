import {Component, ElementRef, OnInit, Renderer2} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Client} from "../../model/Client";
import {ClientService} from "../../service/ClientService";
import {ActivatedRoute, Router} from "@angular/router";
import {AdminService} from "../../service/AdminService";
import {HandlerFlags} from "@angular/compiler-cli/src/ngtsc/transform";
import {BiletService} from "../../service/BiletService";
import {Bilet} from "../../model/Bilet";
import {MovieService} from "../../service/MovieService";
import {CosCumparaturiService} from "../../service/CosCumparaturiService";



@Component({
  selector: 'app-first-page',
  templateUrl: './CinemaSeats.component.html',
  styleUrls: ['./CinemaSeats.component.css']
})
export class CinemaSeatsComponent implements OnInit {

  private handlebar:any;
  private pretTotal:number=0;
  private listaLocuri:string[]=[];
  private pretFilm:number=0;

  constructor(private biletService:BiletService,
              private movieService:MovieService,
              private cosCumparaturiService:CosCumparaturiService,
              public router: Router) { }

  ngOnInit(): void {
    //cd initializez pagina, caut toate biletele pt filmul respectiv si afisez acele scaune cu rosu

   this.colorRed();
    this.findPrice();
    //asa vreau sa adaug bilet pentru un scaun
    const checkboxes = document.querySelectorAll('input[type=checkbox]');
    checkboxes.forEach(checkbox => {
      checkbox.addEventListener('click', () => {
        const inputElement = checkbox as HTMLInputElement;
             if(inputElement.checked && !inputElement.classList.contains('purchased')) {
               console.log(checkbox.id);
               this.listaLocuri.push(checkbox.id);
               this.pretTotal+=this.pretFilm;
               this.f1();
             }
             if(!inputElement.checked && !inputElement.classList.contains('purchased'))
             {
               this.pretTotal-=this.pretFilm;
               this.f1();
             }
      });
    });


  }

 colorRed()
 {
   this.biletService.findOccupiedPlaces(localStorage.getItem("numeFilmCurent")
   ).subscribe
   ((response) =>
     {
       for (let i = 0; i < response.length; i++) {
         const bilet = response[i];
         var element;
         console.log(bilet.locInSala)
         if (typeof bilet.locInSala === "string") {
           element = document.getElementById(bilet.locInSala) as HTMLInputElement;
         }
         if (element) {
           element.nextElementSibling?.setAttribute("style", "background-color: red");
           element.classList.add('purchased')
         }
       }
     },
     (error) => {
     });

 }
  f1() {


    const parent = document.getElementById('total');

    const paragraph = document.getElementById('p');

    if (!paragraph) {
      // create a new paragraph element if it doesn't exist
      const newParagraph = document.createElement('p');
      newParagraph.setAttribute('id', 'p');
      parent?.appendChild(newParagraph);
    } else {
      // update the text of the existing paragraph element
      paragraph.textContent = '' + this.pretTotal;
      paragraph.setAttribute('style', ' position:absolute; left: 790px; top: 848px;');
    }
    }


  pay() {
    console.log(this.listaLocuri)


    for (let i = 0; i < this.listaLocuri.length; i++) {
      var bilet = this.listaLocuri[i];
      var element;
      console.log(bilet)

      element = document.getElementById(bilet) as HTMLInputElement;
      if (element) {
        element.nextElementSibling?.setAttribute("style", "background-color: red");
      }
      //marchez si ca si classlist ca a fost cumparat
      element.classList.add('purchased');

      //ORICE PUN INSIDE THE SUBSCRIBE BRACKETS SUNT UPDATATE DOAR DUPA CE PRIMESTE RASPUNS DE LA SERVER!!
      //apoi urmeaza sa apelez partea de platit biletu si asa se face cu rosu si ce am cumparat eu
     var biletCurent;
      this.biletService.addTicket(localStorage.getItem("numeFilmCurent"), bilet, this.pretFilm).subscribe
      ((response: any) => {
          console.log(response);
          biletCurent=response.id;
          //trebuie sa adaug biletul si in cosul de cumparaturi
          this.cosCumparaturiService.addMovie(biletCurent,localStorage.getItem("nume"),
            localStorage.getItem("prenume")).subscribe
          ((response: any) => {
              console.log(response);
            },
            (error: any) => {
            });
        },
        (error: any) => {
        });



      }
      this.listaLocuri = []
      //resetez pretul total
      this.pretTotal = 0;
      //si il afisez

      this.f1();

  }

  redirectMainPage()
  {
    this.router.navigate(['mainPage'])
  }
  redirectToPayment()
  {
    this.router.navigate(['payPage'])
  }

   findPrice() {
    this.movieService.getPrice(localStorage.getItem("numeFilmCurent")).subscribe
    ((response: any) => {
        console.log(response);
        this.pretFilm=response;
      },
      (error: any) => {
      });

  }
}
