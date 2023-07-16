import {ChangeDetectorRef, Component, ElementRef, Input, OnInit, ViewChild} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Client} from "../../model/Client";
import {Movie,MovieKeys} from "../../model/Movie";
import {MovieService} from "../../service/MovieService";
import {catchError, map, Observable, of} from "rxjs";
import {ActivatedRoute, Router} from "@angular/router";
import {ClientService} from "../../service/ClientService";
import {CosCumparaturi} from "../../model/CosCumparaturi";
import {CosCumparaturiService} from "../../service/CosCumparaturiService";
import {Bilet} from "../../model/Bilet";
import {BiletService} from "../../service/BiletService";
import {error} from "@angular/compiler-cli/src/transformers/util";
import {MatDialog} from "@angular/material/dialog";
import {AccountDialogComponent} from "../AccountDialog/AccountDialog.component";
import {webSocket} from "rxjs/webSocket";
import {MatIconRegistry} from "@angular/material/icon";
import {DomSanitizer} from "@angular/platform-browser";
import {WatchListService} from "../../service/WatchListService";
import * as L from "leaflet";
import {ReviewService} from "../../service/ReviewService";



interface carouselImage
{
  Imagesrc:string;
  imageAlt:string;
}
  @Component({
  selector: 'app-first-page',
  templateUrl: './mainPage.component.html',
  styleUrls: ['./mainPage.component.css']
})
export class MainPageComponent implements OnInit {
  data: Array<String> = ["Ascending", "Descending","Random"];
  searchForm: FormGroup | undefined;
  talkieForm:FormGroup|undefined;
  sortingOrder: string = "";

  word:string="";
  talkie:string="";
  subject=webSocket('ws://localhost:8889/');


  filterForm: FormGroup | undefined;
  filterMainOption:string="";
 // filterMainData: Array<String> = Object.getOwnPropertyNames(Movie.prototype).slice(1);
//asa fac rost de toate fields pt  o clasa in ts=>nu le pot folosi,dar obtin
  //strings lor asa, plus fac de la index 1, pt ca mi ar returna si string"constructor"
  movieInstance = new Movie(undefined, undefined, undefined,undefined,undefined,
    undefined,undefined);
  filterMainData: MovieKeys[] = (Object.keys(this.movieInstance) as MovieKeys[]).slice(1)
  movieList: Movie[] = [];
  cosCumparaturiList:CosCumparaturi[]=[];
  bileteList:Bilet[]=[];
  watchList:Movie[]=[];

  filterFormReal: FormGroup | undefined;
  filterOption:string="";
  clickNr:number=0;
  clickNrFilter:number=0;
  clickNrSearch:number=0;
  clickNrContact:number=0;
  clickNrReview:number=0;
  clickNrInima:number=0;
  total:number=0;
  reviewedMovie:Movie | undefined;
  rating:number=0;



  @Input() images:carouselImage[]=[];
  @Input() indicators=true;
  @Input() controls=true;
  @Input() autoSlide=true;
  @Input() slideInterval=10000;
  selectedIndex=0;
    icon = 'favorite_border'; // the filled heart icon by default

  constructor(private movieService: MovieService,
              private formBuilder: FormBuilder,
              private cosCumparaturiService:CosCumparaturiService,
              private biletService:BiletService,
              private route: ActivatedRoute,
              public router: Router,
              private clientService:ClientService,
              private cdr: ChangeDetectorRef,
              private dialog: MatDialog,
              private matIconRegistry: MatIconRegistry,
              private domSanitizer: DomSanitizer,
              private watchListService:WatchListService,
              private reviewService:ReviewService) {

  }

  ngOnInit(): void {

    this.initForms();
    this.filterMainOption = "name";
    this.sortingOrder = "Random";
    //initial vr sa fiu un get simplu din DB: sa apara ca si cum ar fi random initial pe site
    this.movieService.getChosenOrder("random", "name").subscribe((res) => {
        //  console.log(res);
        this.movieList = res;
      },
      (_error) => {

      });
    this.cosCumparaturiService.getAllFromCos().subscribe((res) => {
        this.cosCumparaturiList = res;
      },
      (_error) => {

      });

    this.getBilete();

    this.images = [    {Imagesrc: "assets/parasite.jpg", imageAlt: "nu"},    {Imagesrc: "assets/coherence.jpg", imageAlt: "nu"},
     ];


    //pt maps


  }

    async toggleHeart(film: any) {
      try {
        if (!film.watchList) {
          // Add movie to watchlist
          await new Promise<void>((resolve, reject) => {
            this.watchListService.addMovieToWatchList(film.name, film.regizor, localStorage.getItem("nume"), localStorage.getItem("prenume"))
              .subscribe(
                (res) => {
                  // console.log(res);
                  resolve();
                  this.cdr.detectChanges();
                },
                (error) => {
                  reject(error);
                }
              );
          });

          // Update watchlist
          const updatedWatchList = await new Promise<any>((resolve, reject) => {
            this.watchListService.updateWatchlist(localStorage.getItem("nume"), localStorage.getItem("prenume"))
              .subscribe(
                (res) => {
                  // console.log(res);
                  resolve(res);
                  this.cdr.detectChanges();
                },
                (error) => {
                  reject(error);
                }
              );
          });

          this.watchList = updatedWatchList;
          film.watchList = updatedWatchList;
          this.cdr.detectChanges();
        } else {
          // Remove movie from watchlist
          await new Promise<void>((resolve, reject) => {
            this.watchListService.deleteFromWatchList(film.name, film.regizor, localStorage.getItem("nume"), localStorage.getItem("prenume"))
              .subscribe(
                (res) => {
                  // console.log(film.name);
                  // console.log(film.regizor);
                  resolve();
                  this.cdr.detectChanges();
                },
                (error) => {
                  reject(error);
                }
              );
          });

          // Update watchlist
          const updatedWatchList = await new Promise<any>((resolve, reject) => {
            this.watchListService.updateWatchlist(localStorage.getItem("nume"), localStorage.getItem("prenume"))
              .subscribe(
                (res) => {
                  // console.log(res);
                  resolve(res);
                  this.cdr.detectChanges();
                },
                (error) => {
                  reject(error);
                }
              );
          });

          this.watchList = updatedWatchList;
          film.watchList = updatedWatchList;
          this.cdr.detectChanges();
        }

        this.cdr.detectChanges();
        console.log(this.watchList);
      } catch (error) {
        console.error(error);
      }
    }

    ngAfterViewInit()
  {
    const buttonElement=document.getElementById("cos");
    const filterButton=document.getElementById("filter");
    const searchButton=document.getElementById("search");
    const contactButton=document.getElementById("contact");
    const favoriteButton=document.getElementById("favorite");
    const reviewButton=document.getElementById("review")

    if(buttonElement!=null)
    buttonElement.addEventListener('click',()=>
    {
      const divCart=document.getElementById("cosulet")
      if(divCart!=null) {
        if (this.clickNr % 2 == 0) {
          divCart.style.visibility = 'visible'
          this.f1();
        }
        else
          divCart.style.visibility = 'hidden'
        this.clickNr++;
      }

          const divSemnCos=document.getElementById("nrItems");
          if(divSemnCos!=null)
          {
            if(this.bileteList.length==0)
                divSemnCos.style.visibility='hidden';
            else divSemnCos.style.visibility='visible';
          }
    })


    if(filterButton!=null)
      filterButton.addEventListener('click',()=>
      {
        const divSearchBox=document.getElementById("filterBox")
        if(divSearchBox!=null) {
          if (this.clickNrFilter % 2 == 0) {
            divSearchBox.style.visibility = 'visible'
          }
          else
            divSearchBox.style.visibility = 'hidden'
          this.clickNrFilter++;
        }
      })

    if(favoriteButton!=null)
      favoriteButton.addEventListener('click',()=>
      {
        const divSearchBox=document.getElementById("favorites")
        if(divSearchBox!=null) {
          if (this.clickNrFilter % 2 == 0) {
            divSearchBox.style.visibility = 'visible'
          }
          else
            divSearchBox.style.visibility = 'hidden'
          this.clickNrFilter++;
        }
      })
    if(searchButton!=null)
      searchButton.addEventListener('click',()=>
      {
        const divSearchBox=document.getElementById("searchBox")
        if(divSearchBox!=null) {
          if (this.clickNrSearch % 2 == 0) {
            divSearchBox.style.visibility = 'visible'
          }
          else
            divSearchBox.style.visibility = 'hidden'
          this.clickNrSearch++;
        }
      })

    if(contactButton!=null)
      contactButton.addEventListener('click',()=>
      {
        const divSearchBox=document.getElementById("contactBox")
        if(divSearchBox!=null) {
          if (this.clickNrContact % 2 == 0) {
            divSearchBox.style.visibility = 'visible'
          }
          else
            divSearchBox.style.visibility = 'hidden'
          this.clickNrContact++;
        }
      })


  }

getBilete()
{
  this.cosCumparaturiService.getAllBileteFromCos().subscribe((res) => {
   // console.log(res);
    this.bileteList=res;


    const divSemnCos=document.getElementById("nrItems");
    if(divSemnCos!=null)
    {
      if(this.bileteList.length==0)
        divSemnCos.style.visibility='hidden';
      else divSemnCos.style.visibility='visible';
    }
  this.total=0;//reactualizez mereu totalul
   // console.log(this.total)
    res.forEach((b)=> {
      //pt toate biletele voi seta campul de movie si de aici smooth sailing..
      // @ts-ignore
      this.total+=b.pret;
      this.biletService.getMovieFromBilet(b.id).subscribe((raspuns: any) => {
          // console.log(raspuns.movie)
          b.movie=raspuns;
        },
        (_error) => {

        });
    });
console.log(this.total)
    this.f1();
this.cdr.detectChanges()
  });

}
initForms() {
    this.searchForm = this.formBuilder.group({
      sortingOrder: ['default', Validators.required],

    })

    this.filterForm = this.formBuilder.group({
      filterMainOption: ['None', Validators.required],

    })
  this.talkieForm = this.formBuilder.group({
    talkie: ['None', Validators.required],

  })
    //aici selectez daca pica dupa ceva criteriu=>gen doar filmele regizate de Scorsese etc
    this.filterFormReal = this.formBuilder.group({
      filterOption: ['None', Validators.required],
      word: ['None', Validators.required],
    })
  }

  selected() {
    console.log(this.sortingOrder);
      this.movieService.getChosenOrder(this.sortingOrder,this.filterMainOption).subscribe((res) => {
          console.log(res);
          this.movieList = res;
        },
        (_error) => {

        });

    }
//se va updata lista de filme each time
  search() {
    console.log(this.filterOption);
    console.log(this.word);
    this.movieService.getRemainingMovies(this.filterOption,this.word).subscribe((res) => {
        console.log(res);
        this.movieList = res;
      },
      (_error) => {

      });
  }

  logOut() {
    var nume = localStorage.getItem("nume");
    var prenume=localStorage.getItem("prenume")
    var email=localStorage.getItem("email")
    if(nume==null) nume="";
    if(prenume==null) prenume="";
    if(email==null) email="";
    this.router.navigate(['guestPage'])
    //aici apelez logout din backend
    this.clientService.logout(nume,prenume,email).subscribe((res) => {
        console.log(res);
      },
      (_error) => {

      });
    localStorage.clear();
  }

  goToCart(nume:any) {
    this.router.navigate(['cinemaSeats'])
    localStorage.setItem("numeFilmCurent",nume);

  }

  goPay() {
    this.router.navigate(['payPage'])
  }


  deleteItem(numeFilm:any,regizor:any,
             locInSala:any) {
    this.cosCumparaturiService.deleteItemFromCos(numeFilm,regizor,locInSala,
      localStorage.getItem("nume"),localStorage.getItem("prenume")).subscribe((res:any)=>
    {
        this.getBilete()
      this.cdr.detectChanges();
      this.f1();

    },
      (_error:any)=>{

    })
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
      //this.cdr.detectChanges()
      // update the text of the existing paragraph element
      paragraph.textContent = '' + this.total;
      paragraph.setAttribute('style', ' position:relative; left: 60px; top:-20px;;');
    }
  }

  deleteAllFromCos() {
    this.cosCumparaturiService.deleteAllFromCos(localStorage.getItem("nume"),localStorage.getItem("prenume")).subscribe((res:any)=>
      {
        this.getBilete()
        console.log(this.bileteList)
        this.cdr.detectChanges();
        this.f1();

      },
      (_error:any)=>{

      })
  }

    //changes slide every 3s
    autoSlideImages():void
    {
      setInterval(()=>
      {
        this.onNextClick()
      },this.slideInterval)
    }


    onPrevClick():void
    {
      if(this.selectedIndex==0)
        this.selectedIndex=this.images.length-1;
      else
        this.selectedIndex--;
    }

    onNextClick():void {

      if(this.selectedIndex==this.images.length-1)
        this.selectedIndex=0;
      else
        this.selectedIndex++;
    }


//efectiv asa trec cu manuta  de pe o imagine pe alta

    selectIndex(index: number) {
      this.selectedIndex = index;
    }


    sendMessage(talkie:any) {
        this.subject.subscribe();
        this.subject.next(this.talkie);
        this.subject.complete();

        this.talkie="";
      const aha = document.getElementById('mesaj');
          if(aha!=null)
        // update the text of the existing paragraph element
        aha.textContent = '';
    }

     selectRating(nr:any)
     {
       this.rating=nr;
       this.cdr.detectChanges();
       console.log(this.rating)
     }
    setReviewedMovie(movie:any) {
      this.reviewedMovie = movie;
      const divSearchBox=document.getElementById("reviewBox")
      console.log("a intrat aici")


      if(divSearchBox!=null) {
        if (this.clickNrReview % 2 == 0) {
          divSearchBox.style.visibility = 'visible'
        }
        else
          divSearchBox.style.visibility = 'hidden'
        this.clickNrReview++;
      }

    }
    sendReview()
    {

       const textarea = document.querySelector('textarea');
     const text = textarea?.value;

     console.log(text)
      this.reviewService.addReview(this.reviewedMovie?.name,this.reviewedMovie?.regizor,this.rating,text,
        localStorage.getItem("nume"),localStorage.getItem("prenume")).subscribe((res:any)=>
        {
        this.cdr.detectChanges()
        },
        (_error:any)=>{

        })
      this.movieService.getAllMovies().subscribe((res)=>
      {
        this.movieList=res;
        this.cdr.detectChanges()
      },
        (_error)=>
        {

        })

      this.cdr.detectChanges()
    }
  }
