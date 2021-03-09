package ru.geekbrains;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductRepository {
    private List<Product> productList = new ArrayList<>();

    public ProductRepository repository(){
        return new ProductRepository();
    }

    public void addProduct(Product product){
        this.productList.add(product);
    }

    public List<Product> getProductList() {
        return productList;
    }
}
