import { Component, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs/internal/Subscription';
import { Banka } from 'src/app/models/banka';
import { BankaService } from 'src/app/services/banka.service';
import { BankaDialogComponent } from '../dialogs/banka-dialog/banka-dialog.component';

@Component({
  selector: 'app-banka',
  templateUrl: './banka.component.html',
  styleUrls: ['./banka.component.css']
})
export class BankaComponent {

  subscription!: Subscription;
  displayedColumns = ['id', 'naziv', 'kontakt','pib', 'actions'];
  dataSourceBanka!: MatTableDataSource<Banka>;
  @ViewChild(MatSort, {static: false}) sort!: MatSort;
  @ViewChild(MatPaginator, {static: false}) paginator!: MatPaginator;

  constructor(private bankaService: BankaService, private dialog: MatDialog) { }

  ngOnInit(): void { this.loadData(); }

  loadData(): void {
    this.subscription = this.bankaService.getAllBanka().subscribe(
      data => {
        this.dataSourceBanka = new MatTableDataSource(data);
        this.dataSourceBanka.sort = this.sort;
        this.dataSourceBanka.paginator = this.paginator;
      },
      error => {
        console.log(error.name + ' ' + error.message);
      }
    );
  }

  public openDialog(flag: number, banka?: Banka) : void {
    const dialogRef = this.dialog.open(BankaDialogComponent, {data: (banka ? banka: new Banka())});
    dialogRef.componentInstance.flagBankaDialog = flag;
    dialogRef.afterClosed().subscribe(res => {if(res==1) this.loadData();})
  }

  applyFilter(filterValue: any) {
    filterValue = filterValue.target.value
    filterValue = filterValue.trim();
    filterValue = filterValue.toLocaleLowerCase();
    this.dataSourceBanka.filter = filterValue; //    UniCredIt    --> UnIcReDiT --> unicredit
  }

  ngOnDestroy(): void { this.subscription.unsubscribe(); }

  ngOnChanges(){this.loadData();}
}
