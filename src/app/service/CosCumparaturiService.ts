import {Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Movie} from "../model/Movie";
import {CosCumparaturi} from "../model/CosCumparaturi";
import {Bilet} from "../model/Bilet";


@Injectable({
  providedIn: 'root'
})
export class CosCumparaturiService {

  baseURL: string = "http://localhost:8081/cosCumparaturi";

  constructor(private httpClient: HttpClient) {
  }
  addMovie(id:any,nume:any,prenume:any,) {
    return this.httpClient.put<CosCumparaturi[]>(this.baseURL+"/addItemToCos?"+"id="+id
      +"&nume="+nume+"&prenume="+prenume,null);
  }
  getAllFromCos() {
    return this.httpClient.get<CosCumparaturi[]>(this.baseURL+"/all");
  }

  getAllBileteFromCos() {
    return this.httpClient.get<Bilet[]>(this.baseURL+"/getAllBilete");
  }

  deleteItemFromCos(numeFilm: any, regizor: any, locInSala: any, nume: any,prenume:any) {
    return this.httpClient.delete<CosCumparaturi>(this.baseURL+"/removeItemFromCos?"
      +"numeFilm="+numeFilm+"&regizor="+regizor+"&locInSala="+locInSala+
      "&nume="+nume+"&prenume="+prenume);
  }

  deleteAllFromCos(nume: any, prenume: any) {
    return this.httpClient.delete<CosCumparaturi[]>(this.baseURL+"/removeAllFromCos?"+
      "nume="+nume+"&prenume="+prenume);
  }
}
