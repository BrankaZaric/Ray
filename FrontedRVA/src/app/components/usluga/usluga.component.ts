import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { UslugaService } from 'src/app/services/usluga.service';
import { Usluga } from 'src/app/models/usluga';
import { KorisnikUsluge } from 'src/app/models/korisnikUsluge';
import { MatSnackBar } from '@angular/material/snack-bar';
import {UslugaDialogComponent } from '../dialogs/usluga-dialog/usluga-dialog.component';

@Component({
  selector: 'app-usluga',
  templateUrl: './usluga.component.html',
  styleUrls: ['./usluga.component.css']
})
export class UslugaComponent implements OnInit, OnDestroy {
  displayedColumns = ['id',  'naziv', 'opisUsluge', 'datumUgovora', 'provizija', 'filijala', 'korisnik', 'actions'];
  dataSource!: MatTableDataSource<Usluga>;
  subscription!: Subscription;
  @Input() selektovaniKorisnik!: KorisnikUsluge;

  constructor(private uslugaService: UslugaService,
    private dialog: MatDialog,
    public snackBar: MatSnackBar) { }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  ngOnInit(): void {
    this.loadData();
  }

  ngOnChanges(): void {
    if (this.selektovaniKorisnik.id) {
      this.loadData();
    }
  }

  loadData() {
    this.subscription = this.uslugaService.getAllForKorisnik(this.selektovaniKorisnik.id)
      .subscribe({
        next: (data) => this.dataSource = data,
        error: (error) => {
          this.snackBar.open('Korisnik nema usluge', 'Zatvori', {
            duration: 2500
          }); this.dataSource = new MatTableDataSource<Usluga>
        },
        complete: () => console.info('complete')
      })
  }
  public openDialog(flag: number, usluga?: Usluga) {
    const dialogRef = this.dialog.open(UslugaDialogComponent, { data: (usluga ? usluga : new Usluga()) });
    dialogRef.componentInstance.flag = flag;
    if (flag === 1) {
      dialogRef.componentInstance.data.korisnik = this.selektovaniKorisnik;
    }
    dialogRef.afterClosed()
      .subscribe(result => {
        if (result === 1) {
          this.loadData();
        }
      })
  }
}