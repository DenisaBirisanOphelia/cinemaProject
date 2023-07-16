import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Client} from "../../model/Client";
import {ClientService} from "../../service/ClientService";
import {ActivatedRoute, Router} from "@angular/router";
import {AdminService} from "../../service/AdminService";

@Component({
  selector: 'app-first-page',
  templateUrl: './first-page.component.html',
  styleUrls: ['./first-page.component.css']
})
export class FirstPageComponent implements OnInit {

  clientList:Client[] = [];
  ownerList:any;
  loginForm:FormGroup|undefined;
  username: string="";
  userprenume:string="";
  userChoice:string="";
  password:string="";
  data:Array<String>=["Client","Admin"];

  constructor(private clientService:ClientService,
              private adminService:AdminService,
              private formBuilder:FormBuilder,
              private route:ActivatedRoute,
              public router: Router) { }

  ngOnInit(): void {
    //first things first, imi va afisa toate movies pe pagina
    // this.movieService.getAllMovies().subscribe((res)=>{
    //     console.log(res);
    //     this.movieList=res;
    //   },
    //   (_error)=>{
    //
    //   });
    //apoi va apela functiile astea si formularul pentru movies
//    this.movieService.(1).subscribe();
  //  this.masinaService.getMasinaByMarca("Logan").subscribe();

    this.initLoginForm();

  }

  initLoginForm(){
    this.loginForm=this.formBuilder.group({
      username:['default', Validators.required],
      userprenume:['default',Validators.required],
      userChoice:['default',Validators.required],
      password:['default',Validators.required],
    })
  }
clearUp()
{
  this.username="";
  this.userprenume="";
  this.userChoice="";
  this.password="";
}
  login() {
    if(this.userChoice=="Client") {
      this.clientService.login(this.loginForm?.value.username,
        this.loginForm?.value.userprenume,
        this.loginForm?.value.password,
        ).subscribe
      ((response:any) =>
        {console.log("login successful");
          console.log(response)
          this.router.navigate(['mainPage'])
          localStorage.setItem("nume",response.nume)
          localStorage.setItem("prenume",response.prenume)
          localStorage.setItem("email",response.email)
          },
        error => {
          console.log("unsuccessful login");
          //aici as putea sa il fortez sa navigheze
          //la alta pagina cu popup u ala
      this.clearUp()});
    }
    else {
      //aici voi verifica pentru admin
      this.adminService.login(this.loginForm?.value.username,
        this.loginForm?.value.userprenume,
        this.loginForm?.value.password,
      ).subscribe
      ((response) =>
        {console.log("login successful");
          this.router.navigate(['adminPage'])
        },
        (error) => {
          console.log("unsuccessful login");
          this.clearUp()});
    }
  }

  selected() {
    console.log(this.userChoice)
  }

}
