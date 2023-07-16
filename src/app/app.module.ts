import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {MatListModule} from '@angular/material/list';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { Route, Router } from '@angular/router';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import  {FirstPageComponent} from "./components/first-page/first-page.component";
import {SignUpPageComponent} from "./components/SignUp-page/SingUp-page.component";
import {MainPageComponent} from "./components/mainPage/mainPage.component";
import {EmailComponent} from "./components/email/email.component";
import {MatLineModule} from "@angular/material/core";
import {AdminPageComponent} from "./components/Admin-page/Admin-page.component";
import {GuestPageComponent} from "./components/guestPage/guestPage.component";
import {MatIconModule} from "@angular/material/icon";
import {MatMenuModule} from "@angular/material/menu";
import {MatButtonModule} from "@angular/material/button";
import {AccountDialogComponent} from "./components/AccountDialog/AccountDialog.component";
import {MatDialogModule} from "@angular/material/dialog";
import {MatTableModule} from "@angular/material/table";
import {CinemaSeatsComponent} from "./components/CinemaSeats/CinemaSeats.component";
import {PayPageComponent} from "./components/payPage/payPage.component";
import {SlideShowComponent} from "./components/SlideShow/SlideShow.component";
import { CommonModule } from '@angular/common';
import {NgxCaptchaModule} from "ngx-captcha";
import {MatLegacyFormFieldModule} from "@angular/material/legacy-form-field";
import {MapComponent} from "./components/Map/Map.component";
import {ReviewPageComponent} from "./components/ReviewPage/ReviewPage.component";

@NgModule({

  declarations: [

    AppComponent,
    FirstPageComponent,
    SignUpPageComponent,
    MainPageComponent,
    EmailComponent,
    AdminPageComponent,
    GuestPageComponent,
    AccountDialogComponent,
    CinemaSeatsComponent,
    PayPageComponent,
    SlideShowComponent,
    MapComponent,
    ReviewPageComponent,
  ],


  imports: [
    BrowserModule,
    AppRoutingModule,
    MatListModule,
    BrowserAnimationsModule,
    HttpClientModule,
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    MatLineModule,
    MatIconModule,
    MatMenuModule,
    MatButtonModule,
    MatDialogModule,
    MatTableModule,
    CommonModule,
    NgxCaptchaModule,
    MatLegacyFormFieldModule,

  ],

  schemas:[CUSTOM_ELEMENTS_SCHEMA, NO_ERRORS_SCHEMA],

  exports: [
    MatListModule,
  ],

 // providers: [User],
  bootstrap: [AppComponent]

})

export class AppModule { }
