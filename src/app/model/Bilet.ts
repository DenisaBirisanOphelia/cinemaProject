import {Movie} from "./Movie";

export class Bilet{
  id: number | undefined;
  locInSala:string | undefined;
  pret:number | undefined;
  movie:Movie|undefined;

  constructor(id: number | undefined, locInSala: string | undefined, pret: number | undefined, movie: Movie | undefined) {
    this.id = id;
    this.locInSala = locInSala;
    this.pret = pret;
    this.movie = movie;
  }
}
