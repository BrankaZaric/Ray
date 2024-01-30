import { KorisnikUsluge } from "./korisnikUsluge";
import { Filijala } from "./filijala";

export class Usluga {
    id!: number;
    naziv!: string;
    opisUsluge!: string;
    datumUgovora!: Date;
    provizija!: number;
    korisnik!: KorisnikUsluge;
    filijala!: Filijala;
}