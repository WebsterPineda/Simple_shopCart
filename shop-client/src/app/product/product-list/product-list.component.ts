import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ApiAnswer } from 'src/app/models/api-answer.model';
import { Product } from 'src/app/models/product.model';
import { ProductService } from 'src/app/services/product-service.service';
import { DialogProductComponent } from '../dialog/dialog-product/dialog-product.component';
import { DialogProductReadonlyComponent } from '../dialog/dialog-product-readonly/dialog-product-readonly.component';
import { DeleteDialogComponent } from 'src/app/common/dialog/delete-dialog/delete-dialog.component';

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.scss'],
})
export class ProductListComponent implements OnInit {
  readonly duration: number = 2000;
  readonly width: string = '500px';
  products: Product[] = [];
  displayColumns: string[] = ['id', 'descripcion', 'actions'];

  constructor(
    private productService: ProductService,
    private dialog: MatDialog,
    private snack: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.getProducts();
  }

  getProducts(): void {
    this.productService.getProducts().subscribe({
      next: (rsp: ApiAnswer<Product[]>) => {
        if (rsp && rsp.code === 0) {
          const data = rsp.data;
          if (data) {
            this.products = data;
          }
        }
      },
      error: (err) => {
        if (err) {
          this.snack.open(
            'No es posible obtener los productos ' + err.message,
            '',
            {
              duration: this.duration,
            }
          );
        }
      },
    });
  }

  createProduct(): void {
    const dialogRef = this.dialog.open(DialogProductComponent, {
      width: this.width,
    });

    dialogRef.componentInstance.formSubmit.subscribe((rst) => {
      if (rst) {
        let producto: ProductImpl = new ProductImpl();

        producto.descripcion = rst.descripcion;
        producto.unidadMedida = rst.unidadMedida;
        producto.precio = rst.precio;

        this.productService.addProduct(producto).subscribe({
          next: (rsp) => {
            if (rsp && rsp.code == 0) {
              const data = rsp.data;
              if (data) {
                this.snack.open('El producto se ha registrado con exito', '', {
                  duration: this.duration,
                });
              }
            }
          },
          error: (err) => {
            if (err) {
              this.snack.open(
                'No fue posible crear el producto ' + err.message,
                '',
                {
                  duration: this.duration,
                }
              );
            }
          },
        });
      }
    });

    dialogRef.afterClosed().subscribe(() => this.getProducts());
  }

  viewProduct(product: ProductImpl): void {
    const dialogRef = this.dialog.open(DialogProductReadonlyComponent, {
      width: this.width,
      data: product,
    });

    dialogRef.afterClosed().subscribe(() => this.getProducts());
  }

  editProduct(product: ProductImpl): void {
    const dialogRef = this.dialog.open(DialogProductComponent, {
      width: this.width,
      data: product,
    });

    dialogRef.componentInstance.formSubmit.subscribe((rst) => {
      if (rst) {
        product.descripcion = rst.descripcion;
        product.unidadMedida = rst.unidadMedida;
        product.precio = rst.precio;

        this.productService.updateProduct(product.id, product).subscribe({
          next: (rst) => {
            if (rst && rst.code === 0) {
              this.snack.open('Producto actualizado con exito', '', {
                duration: this.duration,
              });
            }
          },
          error: (err) => {
            if (err) {
              this.snack.open('Error: ' + err.message, '', {
                duration: this.duration,
              });
            }
          },
        });
      }
    });

    dialogRef.afterClosed().subscribe(() => this.getProducts());
  }

  deleteDialog(product: ProductImpl): void {
    const dialogRef = this.dialog.open(DeleteDialogComponent, {
      width: this.width,
    });

    dialogRef.afterClosed().subscribe({
      next: (rst) => {
        if (rst && rst === 'true') {
          this.deleteProduct(product);
        }
      },
    });
  }

  deleteProduct(product: ProductImpl): void {
    this.productService.deleteProduct(product.id).subscribe({
      next: () => {
        this.snack.open('Producto eliminado con exito', '', {
          duration: this.duration,
        });
        this.getProducts();
      },
      error: (err) => {
        if (err) {
          this.snack.open('Error: ' + err.message, '', {
            duration: this.duration,
          });
        }
        this.getProducts();
      },
    });
  }
}

export class ProductImpl implements Product {
  id: number = -1;
  descripcion: string = '';
  unidadMedida: string = '';
  precio: number = 0.0;
}
