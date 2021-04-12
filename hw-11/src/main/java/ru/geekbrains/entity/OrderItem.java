package ru.geekbrains.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price_per_product")
    private int PricePerProduct;

    @Column(name = "price")
    private int price;

    public OrderItem(Product product){
        this.product = product;
        this.quantity = 1;
        this.PricePerProduct = product.getPrice();
        this.price = this.PricePerProduct;
    }

    public void incrementQuantity(){
        quantity++;
        price = quantity * PricePerProduct;
    }

    public void decrementQuantity(){
        quantity--;
        price = quantity * PricePerProduct;
    }
}
