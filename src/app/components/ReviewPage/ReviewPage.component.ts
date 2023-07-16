import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Client} from "../../model/Client";
import {Movie,MovieKeys} from "../../model/Movie";
import {MovieService} from "../../service/MovieService";
import {catchError, map, Observable, of} from "rxjs";
import {ActivatedRoute, Route, Router} from "@angular/router";
import {Review} from "../../model/Review";
import {ReviewService} from "../../service/ReviewService";
@Component({
  selector: 'app-first-page',
  templateUrl: './ReviewPage.component.html',
  styleUrls: ['./ReviewPage.component.css']
})
export class ReviewPageComponent implements OnInit {

  constructor(private formBuilder: FormBuilder,
              private reviewService:ReviewService,
              public router: Router) {
  }

  reviewList:Review[]=[];

  ngOnInit(): void {
    this.reviewService.getAllReviews().subscribe((res)=>
      {
        this.reviewList=res;
        res.forEach((review)=> {
       ;
          this.reviewService.getMovieFromReview(review.id).subscribe((raspuns: any) => {
              // console.log(raspuns.movie)

              review.movie=raspuns;
            },
            (_error) => {

            });

          this.reviewService.getClientFromReview(review.id).subscribe((raspuns: any) => {
              // console.log(raspuns.movie)
              console.log(raspuns)
              review.client=raspuns;
            },
            (_error) => {

            });
        });


      },
      (_error)=>
      {

      })
  }
}

