import {Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Client} from "../model/Client";

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  baseURL: string = "http://localhost:8081/client";

  constructor(private httpClient: HttpClient) {
  }

   login(nume: string,prenume:string,pass:string) {
     const url = `${this.baseURL}/logIn?nume=${nume}&prenume=${prenume}&pass=${pass}`;
   return this.httpClient.put(url,{});
   }
  logout(nume: string,prenume:string,email:string) {
    return this.httpClient.put(this.baseURL + "/logOut?nume="+nume+"&prenume="+prenume
      +"&email="+email,null);
  }
  signup(nume: string,prenume:string,pass:string,email:string) {
    const url = `${this.baseURL}/addClient?nume=${nume}&prenume=${prenume}&pass=${pass}&email=${email}`;
    return this.httpClient.put(url, {});
  }

  readyToGo(email:string,confirm:boolean) {
    console.log(email)
    const url = `${this.baseURL}/updateClient?email=${email}`;
    console.log(url)
    return this.httpClient.put(url, {});
  }

  findLoggedClients()
  {
    return this.httpClient.get<Client[]>(this.baseURL + "/findLoggedClients");
  }

  getAllClients() {
    return this.httpClient.get<Client[]>(this.baseURL + "/findAll");
  }

  getAllClientsInXML() {
    return this.httpClient.get<Client[]>(this.baseURL + "/data");
  }
}

