import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";


@Injectable({
  providedIn: 'root'
})
export class BillService {

  baseURL: string = "http://localhost:8081/bill";

  constructor(private httpClient: HttpClient) {
  }

  createBill(nume: any, prenume:any,email:any,adresa:any,
             city:any,state:any) {
    const url = `${this.baseURL}/createBill?nume=${nume}&prenume=${prenume}&email=${email}&adresa=${adresa}&city=${city}
    &state=${state}`;
    return this.httpClient.put(url,{});
  }

}

