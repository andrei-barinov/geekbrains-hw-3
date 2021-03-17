package ru.geekbrains.hw_6.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@NoArgsConstructor
@Data
@Entity

public class Product {
    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Id //Первичный ключ
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "name", length = 128)
    private String name;

    @Column(name = "price", length = 128)
    private int price;


    @Override
    public String toString() {
        return "Product: [" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ']';
    }
}
