import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {FirstPageComponent} from "./components/first-page/first-page.component";
import {SignUpPageComponent} from "./components/SignUp-page/SingUp-page.component";
import {MainPageComponent} from "./components/mainPage/mainPage.component";
import {EmailComponent} from "./components/email/email.component";
import {AdminPageComponent} from "./components/Admin-page/Admin-page.component";
import {GuestPageComponent} from "./components/guestPage/guestPage.component";
import {CinemaSeatsComponent} from "./components/CinemaSeats/CinemaSeats.component";
import {PayPageComponent} from "./components/payPage/payPage.component";
import {SlideShowComponent} from "./components/SlideShow/SlideShow.component";
import {MapComponent} from "./components/Map/Map.component";
import {ReviewPageComponent} from "./components/ReviewPage/ReviewPage.component";

const routes: Routes = [
  {path:'guestPage', component:GuestPageComponent},
  {path:'', component:GuestPageComponent},
  {path:'signup',component:SignUpPageComponent},
  {path:'mainPage',component:MainPageComponent},
  {path:'adminPage',component:AdminPageComponent},
  {path:'logIn',component:FirstPageComponent},
  {path:'cinemaSeats',component:CinemaSeatsComponent},
  {path:'payPage',component:PayPageComponent},
  {path:'honey',component:SlideShowComponent},
  {path:'da',component:MapComponent},
  {path:'reviews',component:ReviewPageComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
