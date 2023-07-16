import {Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Movie} from "../model/Movie";
import {Bilet} from "../model/Bilet";
import {Observable} from "rxjs";


@Injectable({
  providedIn: 'root'
})
export class BiletService {

  baseURL: string = "http://localhost:8081/bilet";

  constructor(private httpClient: HttpClient) {
  }

  findOccupiedPlaces(parasite: any) {
    return this.httpClient.get<Bilet[]>(this.baseURL+"/findBileteByNumeFilm?"+"nume="+parasite);
  }

  addTicket(nume: any, locInSala: any, pret: any) {
    return this.httpClient.put<Bilet>(this.baseURL+"/addBilet?"+"nume="+nume+"&locInSala="+locInSala+
      "&pret="+pret,null);
  }
  getMovieFromBilet(id: any):Observable<Movie> {
    return this.httpClient.get<Movie>(this.baseURL+"/getMovieFromBilet?id="+id);
  }


}
