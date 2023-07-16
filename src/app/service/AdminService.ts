import {Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";


@Injectable({
  providedIn: 'root'
})
export class AdminService {

  baseURL: string = "http://localhost:8081/admin";

  constructor(private httpClient: HttpClient) {
  }

  login(nume: string,prenume:string,pass:string) {
    return this.httpClient.get(this.baseURL + "/findNumePrenumePass?nume="+nume+"&prenume="+prenume
      +"&pass="+pass);
  }



}

