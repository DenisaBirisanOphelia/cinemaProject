import {AfterViewInit, Component, ElementRef, OnInit, Renderer2} from '@angular/core';
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
import * as L from 'leaflet';



@Component({
  selector: 'app-first-page',
  templateUrl: './Map.component.html',
  styleUrls: ['./Map.component.css']
})
export class MapComponent  {
  constructor() {}

  ngOnInit(): void {
    var map = L.map('map').setView([46.7559286343842, 23.58956457250903], 15);

    const customIcon = L.icon({
      iconUrl: '../../assets/marker.png',
      iconSize: [30, 30],
    });

    const marker = L.marker([46.7559286343842, 23.58956457250903], { icon: customIcon }).addTo(map);


    L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
      maxZoom: 19,
      attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
    }).addTo(map);
  }
}
