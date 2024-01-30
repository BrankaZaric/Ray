import { Component, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { KorisnikUsluge } from 'src/app/models/korisnikUsluge';
import { KorisnikUslugeService } from 'src/app/services/korisnikUsluge.service';
import { KorisnikDialogComponent } from '../dialogs/korisnik-dialog/korisnik-dialog.component';

@Component({
  selector: 'app-korisnik',
  templateUrl: './korisnik.component.html',
  styleUrls: ['./korisnik.component.css']
})
export class KorisnikComponent {
  subscription!: Subscription;
  displayedColumns = ['id', 'ime', 'prezime', 'maticniBroj', 'actions'];
  dataSource!: MatTableDataSource<KorisnikUsluge>;
  selektovaniKorisnik1!: KorisnikUsluge;
  @ViewChild(MatSort, { static: false }) sort!: MatSort;
  @ViewChild(MatPaginator, { static: false }) paginator!: MatPaginator;

  constructor(private korisnikService: KorisnikUslugeService, private dialog: MatDialog) { }

  ngOnInit(): void { this.loadData(); }
  ngOnChanges(): void { this.loadData(); }

  public loadData() {
    this.subscription = this.korisnikService.getAllKorisnici().subscribe(
      data => {
        //console.log(data);
        this.dataSource = new MatTableDataSource(data);
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      },
      error => {
        console.log(error.name + ' ' + error.message);
      }
    );
  }

  openDialog(flag: number, korisnik?: KorisnikUsluge): void {
    const dialogRef = this.dialog.open(KorisnikDialogComponent, { data: (korisnik ? korisnik : new KorisnikUsluge()) });
    dialogRef.componentInstance.flagKorisnikDialog = flag;
    dialogRef.afterClosed().subscribe(res => {
      if (res === 1) //uspesno 
      {
        //ponovo učitaj podatke
        this.loadData();
      }
    })
  }

  ngOnDestroy(){
    this.subscription.unsubscribe();
  }

  selectRow(row: any) {
    this.selektovaniKorisnik1 = row;
  }

  applyFilter(filterValue: any) {
    filterValue = filterValue.target.value
    filterValue = filterValue.trim();
    filterValue = filterValue.toLocaleLowerCase();
    this.dataSource.filter = filterValue; //    JaBuKa    --> JaBuKa --> jabuka
  }
}