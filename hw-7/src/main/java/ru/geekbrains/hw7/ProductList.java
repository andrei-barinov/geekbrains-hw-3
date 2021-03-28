package ru.geekbrains.hw7;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.geekbrains.hw7.entity.Product;

import java.util.List;

@AllArgsConstructor
@Data
public class ProductList {
    private List<Product> productList;
}
