package ru.geekbrains.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.entity.Product;

@Data
@NoArgsConstructor
public class ProductDTO {
    private Long id;
    private String title;
    private int price;

    public ProductDTO(Product p) {
        this.id = p.getId();
        this.title = p.getTitle();
        this.price = p.getPrice();
    }
}
