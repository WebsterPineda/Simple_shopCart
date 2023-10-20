import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ClientListComponent } from './client-list/client-list.component';
import { ClientAddComponent } from './client-add/client-add.component';
import { ClientEditComponent } from './client-edit/client-edit.component';
import { ClientDetailComponent } from './client-detail/client-detail.component';


const routes: Routes = [
  { path: 'client', component: ClientListComponent},
  { path: 'client/:id', component: ClientDetailComponent},
  { path: 'client/create', component: ClientAddComponent},
  { path: 'client/:id/edit', component: ClientEditComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
