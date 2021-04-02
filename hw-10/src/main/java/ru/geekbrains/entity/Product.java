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
    @Column(name = "id")
    @ApiModelProperty(value = "id товара", required = false)
    private Long id;

    @Column(name = "title", length = 128)
    @ApiModelProperty(value = "Название товара", required = true)
    private String title;

    @ApiModelProperty(name = "Цена товара", required = true)
    @Column(name = "price")
    private int price;
}
