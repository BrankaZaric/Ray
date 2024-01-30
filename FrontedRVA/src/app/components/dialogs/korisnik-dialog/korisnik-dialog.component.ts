import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { KorisnikUsluge } from 'src/app/models/korisnikUsluge';
import { KorisnikUslugeService } from 'src/app/services/korisnikUsluge.service';

@Component({
  selector: 'app-korisnik-dialog',
  templateUrl: './korisnik-dialog.component.html',
  styleUrls: ['./korisnik-dialog.component.css']
})
export class KorisnikDialogComponent {
    
  public flagKorisnikDialog!: number;

  constructor(public snackBar: MatSnackBar,
    public korisnikService: KorisnikUslugeService,
    @Inject(MAT_DIALOG_DATA) public dataKorisnik: KorisnikUsluge,
    public dialogRef: MatDialogRef<KorisnikDialogComponent>) { }

  public add(): void {
    console.log("ID je " + this.dataKorisnik.id + this.dataKorisnik.ime);
    this.korisnikService.addKorisnik(this.dataKorisnik).subscribe(() => {
      this.snackBar.open('Uspesno dodat korisnik: ' + this.dataKorisnik.ime, 'OK', {
        duration: 2500
      })
    }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message)
        this.snackBar.open('Doslo je do greske prilikom dodavanja novog korisnika. ', 'Zatvori', {
          duration: 2500
        })
      };
  }


  public update(): void {
    this.korisnikService.updateKorisnik(this.dataKorisnik).subscribe(() => {
      this.snackBar.open('Uspesno izmenjen korisnik: ' + this.dataKorisnik.ime, 'OK', {
        duration: 2500
      })
    }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message)
        this.snackBar.open('Doslo je do greske prilikom izmene korisnika. ', 'Zatvori', {
          duration: 2500
        })
      };

  }

  public delete(): void {
    this.korisnikService.deleteKorisnik(this.dataKorisnik.id).subscribe(() => {
      this.snackBar.open('Uspesno obrisan korisnik: ' + this.dataKorisnik.ime, 'OK', {
        duration: 2500
      })
    }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message)
        this.snackBar.open('Doslo je do greske prilikom brisanja korisnika. ', 'Zatvori', {
          duration: 2500
        })
      };
  }

  public cancel(): void {
    this.dialogRef.close();
    this.snackBar.open('Odustali ste od izmene. ', 'Zatvori', {
      duration: 1000
    })
  }
}