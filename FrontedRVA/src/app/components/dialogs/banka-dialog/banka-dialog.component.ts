import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Banka } from 'src/app/models/banka';
import { BankaService } from 'src/app/services/banka.service';

@Component({
  selector: 'app-banka-dialog',
  templateUrl: './banka-dialog.component.html',
  styleUrls: ['./banka-dialog.component.css']
})
export class BankaDialogComponent {
  public flagBankaDialog!: number;

  constructor(public snackBar: MatSnackBar,
    public bankaService: BankaService,
    @Inject(MAT_DIALOG_DATA) public dataBanka: Banka,
    public dialogRef: MatDialogRef<BankaDialogComponent>) { }

  public add(): void {
    console.log("ID je " + this.dataBanka.id + this.dataBanka.naziv);
    this.bankaService.addBanka(this.dataBanka).subscribe(() => {
      this.snackBar.open('Uspesno dodata banka: ' + this.dataBanka.naziv, 'OK', {
        duration: 2500
      })
    }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message)
        this.snackBar.open('Doslo je do greske prilikom dodavanja nove banke. ', 'Zatvori', {
          duration: 2500
        })
      };
  }


  public update(): void {
    this.bankaService.updateBanka(this.dataBanka).subscribe(() => {
      this.snackBar.open('Uspesno izmenjena banka: ' + this.dataBanka.naziv, 'OK', {
        duration: 2500
      })
    }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message)
        this.snackBar.open('Doslo je do greske prilikom izmene banke. ', 'Zatvori', {
          duration: 2500
        })
      };

  }

  public delete(): void {
    this.bankaService.deleteBanka(this.dataBanka.id).subscribe(() => {
      this.snackBar.open('Uspesno obrisana banka: ' + this.dataBanka.naziv, 'OK', {
        duration: 2500
      })
    }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message)
        this.snackBar.open('Doslo je do greske prilikom brisanja banke. ', 'Zatvori', {
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