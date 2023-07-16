import {Movie} from "./Movie";
import {Client} from "./Client";

export class Review{
  id: number | undefined;
  stele:number | undefined;
  comentariu:string | undefined;
  movie:Movie |undefined;
  client:Client |undefined;

}
