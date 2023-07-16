import {Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Movie} from "../model/Movie";
import {Review} from "../model/Review";

@Injectable({
  providedIn: 'root'
})
export class ReviewService {

  baseURL: string = "http://localhost:8081/review";

  constructor(private httpClient: HttpClient) {
  }

  addReview(nume: any,regizor:any,stele:any,comentariu:any,numeC:any,prenume:any) {
    return this.httpClient.put(this.baseURL+"/addReview?"+"nume="+nume
      +"&regizor="+regizor+"&stele="+stele+"&comentariu="+comentariu+"&numeClient="+numeC+
      "&prenumeClient="+prenume,null);
  }


  getAllReviews() {
    return this.httpClient.get<Review[]>(this.baseURL+"/getAllReviews");
  }

  getMovieFromReview(id: any ) {
    return this.httpClient.get<Movie>(this.baseURL+"/getMovieFromReview?id="+id);
  }
  getClientFromReview(id: any ) {
    return this.httpClient.get<Movie>(this.baseURL+"/getClientFromReview?id="+id);
  }

}
