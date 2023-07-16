import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders, HttpParams} from "@angular/common/http";
import {Movie} from "../model/Movie";
import {map, Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class MovieService {

  baseURL: string = "http://localhost:8081/movie";

  constructor(private httpClient: HttpClient) {
  }

  getAllMovies() {
    return this.httpClient.get<Movie[]>(this.baseURL+"/findAllMovies");
  }
  getChosenOrder(order:string,criteriu:string) {
    return this.httpClient.get<Movie[]>(this.baseURL+"/findChosenOrderMovies?"+"order="+order
    +"&criteriu="+criteriu);
  }

  getPhoto(nume: string | undefined): Observable<string> {
    return this.httpClient.get(this.baseURL+"/getImageForMovie?"+"name="+nume, { responseType: 'arraybuffer' }).pipe(
      map((response:any) => {
        const base64Image = btoa(
          new Uint8Array(response)
            .reduce((data, byte) => data + String.fromCharCode(byte), '')
        );
        return base64Image;
      })
    );
  }


  getRemainingMovies(filterOption: string, word: string) {
    return this.httpClient.get<Movie[]>(this.baseURL+"/findChosenMovies?"+"filterOption="+filterOption
      +"&word="+word);
  }

  updateMovie(nume: string, updateOption: string, newValue: string) {
    return this.httpClient.put<Movie[]>(this.baseURL+"/updateMovie?"+"name="+nume
      +"&updateOption="+updateOption+"&newValue="+newValue,null);
  }
  updateMovie2(nume: string, updateOption: string, newValue: number) {
    return this.httpClient.put<Movie[]>(this.baseURL+"/updateMovie2?"+"name="+nume
      +"&updateOption="+updateOption+"&newValue="+newValue,null);
  }

  deleteMovie(fieldOption: string, filedValue: string) {
    return this.httpClient.delete<Movie[]>(this.baseURL+"/deleteMovie?"
      +"deleteOption="+fieldOption+"&value="+filedValue);
  }
  deleteMovie2(fieldOption: string, filedValue: number) {
    return this.httpClient.delete<Movie[]>("localhost:8081/movie/deleteMovie2?deleteOption=rating&value="+filedValue);
  }

  insert(numeFilm: any, regizor: any, rating: any) {
    return this.httpClient.put<Movie[]>(this.baseURL+"/addMovie?"+"numeFilm="+numeFilm
      +"&regizor="+regizor+"&rating="+rating,null);
  }

  getPrice(item:any) {
    return this.httpClient.get<number>(this.baseURL+"/getPriceForMovie?numeFilm="+item);
  }
}
