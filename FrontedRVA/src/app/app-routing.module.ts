
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BankaComponent } from './components/banka/banka.component';
import { FilijalaComponent } from './components/filijala/filijala.component';
import { KorisnikComponent } from './components/korisnik/korisnik.component';

const routes: Routes =  [ { path: 'banka', component: BankaComponent },   
{ path: 'filijala', component: FilijalaComponent },
{ path: 'korisnik', component: KorisnikComponent },
{ path: '', redirectTo: '/banka', pathMatch: 'full'}
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }