import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { MatSidenavModule } from '@angular/material/sidenav';
import { MatTableModule } from '@angular/material/table';
import { MatDialogModule } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ClientListComponent } from './client/client-list/client-list.component';
import { HttpClientModule } from '@angular/common/http';
import { HomeComponent } from './home/home.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';
import { ProductListComponent } from './product/product-list/product-list.component';
import { SellListComponent } from './sell-list/sell-list.component';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { DialogClienteComponent } from './client/dialog/dialog-cliente/dialog-cliente.component';
import { DeleteDialogComponent } from './common/dialog/delete-dialog/delete-dialog.component';
import { ReadOnlyComponent } from './client/dialog/read-only/read-only.component';
import { DialogProductReadonlyComponent } from './product/dialog/dialog-product-readonly/dialog-product-readonly.component';
import { DialogProductComponent } from './product/dialog/dialog-product/dialog-product.component';

@NgModule({
  declarations: [
    AppComponent,
    ClientListComponent,
    HomeComponent,
    ProductListComponent,
    SellListComponent,
    DialogClienteComponent,
    DeleteDialogComponent,
    ReadOnlyComponent,
    DialogProductReadonlyComponent,
    DialogProductComponent,
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    FormsModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    MatSidenavModule,
    MatTableModule,
    MatDialogModule,
    MatButtonModule,
    MatInputModule,
    MatSnackBarModule,
    ReactiveFormsModule,
    MatIconModule
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
