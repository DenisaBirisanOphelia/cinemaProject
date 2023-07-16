import {Component, Input, OnInit} from '@angular/core';
import {Form, FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {Client} from "../../model/Client";
import {ClientService} from "../../service/ClientService";
import {ActivatedRoute, Router} from "@angular/router";
import {EmailService1} from "../../service/EmailService1";
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';

interface carouselImage
{
  Imagesrc:string;
  imageAlt:string;
}

@Component({
  selector: 'app-first-page',
  templateUrl: './SlideShow.component.html',
  styleUrls: ['./SlideShow.component.css']
})
export class SlideShowComponent implements OnInit {

  FormData: FormGroup|undefined;
  EmailAddress:string='';
  Body:string='';


  @Input() images:carouselImage[]=[];
  @Input() indicators=true;
  @Input() controls=true;
  @Input() autoSlide=true;
  @Input() slideInterval=10000;
  selectedIndex=0;

  constructor(private sanitizer: DomSanitizer) { }

  ngOnInit(): void {
     this.images = [    {Imagesrc: "assets/parasite.jpg", imageAlt: "nu"},    {Imagesrc: "assets/coherence.jpg", imageAlt: "nu"}  ];

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



}
