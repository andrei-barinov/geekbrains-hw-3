package ru.geekbrains.dao;

import org.springframework.stereotype.Component;
import ru.geekbrains.Product;

import java.util.ArrayList;
import java.util.List;

@Component
public class RepositoryDAO {
    private static int PRODUCT_COUNT;
    private List<Product> productList;

    {
        productList = new ArrayList<>();

        productList.add(new Product(++PRODUCT_COUNT, "Orange", 10));
        productList.add(new Product(++PRODUCT_COUNT, "Banana", 20));
        productList.add(new Product(++PRODUCT_COUNT, "Kiwi", 30));
        productList.add(new Product(++PRODUCT_COUNT, "Pineapple", 40));
        productList.add(new Product(++PRODUCT_COUNT, "Apple", 50));

    }

    public List<Product> index(){
        return productList;
    }

    public Product show(int id){
        return productList.stream().filter(product -> product.getId() == id).findAny().orElse(null);
    }

    public  void save(Product product){
        product.setId(++PRODUCT_COUNT);
        productList.add(product);
    }
}
