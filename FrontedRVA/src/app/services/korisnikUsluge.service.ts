import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { KORISNIKUSLUGE_URL } from '../app.constant';
import { KorisnikUsluge } from '../models/korisnikUsluge';

@Injectable({
  providedIn: 'root'
})
export class KorisnikUslugeService {

  constructor(private httpClient: HttpClient) { }

  public getAllKorisnici(): Observable<any> {
    return this.httpClient.get(`${KORISNIKUSLUGE_URL}`);
  }

  public addKorisnik(korisnikUsluge: KorisnikUsluge): Observable<any> {
    return this.httpClient.post(KORISNIKUSLUGE_URL, korisnikUsluge);
  }

  public deleteKorisnik(id: number): Observable<any> {
    return this.httpClient.delete(KORISNIKUSLUGE_URL  + "/" + id);
  }

  public updateKorisnik(korisnikUsluge: KorisnikUsluge) : Observable<any>{
    return this.httpClient.put(KORISNIKUSLUGE_URL + "/" + korisnikUsluge.id, korisnikUsluge);  }
}