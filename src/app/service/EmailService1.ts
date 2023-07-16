import {Injectable} from "@angular/core";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Client} from "../model/Client";
import {catchError, map, throwError} from "rxjs";
import {FormGroup} from "@angular/forms";

@Injectable({
  providedIn: 'root'
})
export class EmailService1 {

  url: string = "https://mailthis.to/denisaophelia2000@yahoo.com";

  constructor(private httpClient: HttpClient) {
  }


  SendEmail(input:any) {
    const options = { responseType: 'text' as const };
    return this.httpClient.post(this.url, input,options)
      .pipe(
        catchError(error => {
          console.error(error);
          return throwError("An error occurred while sending the email.");
        })
      );
  }
}

