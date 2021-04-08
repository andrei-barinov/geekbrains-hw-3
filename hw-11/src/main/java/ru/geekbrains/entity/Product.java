package ru.geekbrains.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@ApiModelProperty(value = "id товара", required = false)
    @Column(name = "id")
    private Long id;

    //@ApiModelProperty(value = "Название товара", required = true)
    @Column(name = "title", length = 128)
    private String title;

    //@ApiModelProperty(name = "Цена товара", required = true)
    @Column(name = "price")
    private int price;
}
