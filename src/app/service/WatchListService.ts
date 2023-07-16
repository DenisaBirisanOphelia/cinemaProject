import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {WatchList} from "../model/WatchList";
import {Movie} from "../model/Movie";

@Injectable({
  providedIn: 'root'
})
export class WatchListService {

  baseURL: string = "http://localhost:8081/watchList";

  constructor(private httpClient: HttpClient) {
  }

  addMovieToWatchList(nume: any, regizor: any, nume1: any,prenume:any) {
    return this.httpClient.put<WatchList>(this.baseURL+"/addToWatchList?"+"nume="+nume
      +"&regizor="+regizor+"&nume1="+nume1+"&prenume="+prenume,null);
  }


  updateWatchlist(item: any, item2: any) {
    return this.httpClient.get<Movie[]>(this.baseURL+"/getMoviesFromWatchList?"+"nume="+item
      +"&prenume="+item2);
  }
  deleteFromWatchList(nume: any, regizor: any,nume1:any,prenume:any) {
    return this.httpClient.delete(this.baseURL+"/deleteMovieFromWatchList?"+"nume="+nume
      +"&regizor="+regizor+"&nume1="+nume1+"&prenume="+prenume);
  }
}
