export class Movie{
  id: number | undefined;
  name:string | undefined;
  regizor :number |undefined;
  rating:number |undefined;
  imageSrc:string|undefined;
  data:Date|undefined;
  pret:number|undefined;
  watchList:Movie[]|undefined;

  constructor(id: number | undefined, name: string | undefined, regizor: number | undefined, rating: number | undefined, imageSrc: string | undefined, data: Date | undefined, pret: number | undefined) {
    this.id = id;
    this.name = name;
    this.regizor = regizor;
    this.rating = rating;
    this.imageSrc = imageSrc;
    this.data = data;
    this.pret = pret;
  }
}

export type MovieKeys= keyof Movie;
