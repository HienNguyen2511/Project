import {Product} from "./product";

export class CartItem {
  id: string | any;
  name: string | any;
  imageUrl: string | any;
  unitPrice: number;

  quantity: number;

  constructor(product: Product) {
    this.id = product.id;
    this.name = product.name;
    this.imageUrl = product.imageUrl;
    // @ts-ignore
    this.unitPrice = product.unitPrice;

    this.quantity = 1;
  }
}
