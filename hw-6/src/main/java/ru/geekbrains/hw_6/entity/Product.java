package ru.geekbrains.hw_6.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


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

    @ManyToMany
    @JoinTable(name = "product_person",
            joinColumns = @JoinColumn(name ="product_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))
    private List<Person> persons;

    @Override
    public String toString() {
        return String.format("[product: %s, price: %s]", getName(), getPrice());
    }

    public void printProduct(){
        System.out.println("----------------------------");
        System.out.println("Товар: " + this.getName() + " | " +
                "Цена: " + this.getPrice());
        System.out.println("----------------------------");
    }
}
