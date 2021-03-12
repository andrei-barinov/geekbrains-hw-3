package ru.geekbrains.boot.repositories;

import org.springframework.stereotype.Component;
import ru.geekbrains.boot.model.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class ProductRepository {
    private List<Product> productList;
    private static int PRODUCT_COUNT;

    @PostConstruct
    public void init(){
        this.productList = new ArrayList<>(Arrays.asList(
                new Product(++PRODUCT_COUNT, "Orange", 10),
                new Product(++PRODUCT_COUNT, "Banana", 20),
                new Product(++PRODUCT_COUNT, "Kiwi", 30),
                new Product(++PRODUCT_COUNT, "Pineapple", 40),
                new Product(++PRODUCT_COUNT, "Apple", 50)
        ));
    }

    public Product save(Product product){
        product.setId(++PRODUCT_COUNT);
        for(int i=0; i < productList.size(); i++){
            if(productList.get(i).getName().equals(product.getName()) && productList.get(i).getPrice() == product.getPrice()) break;
            else productList.add(product);
        }
        return product;
    }

    public List<Product> findAll(){
        return Collections.unmodifiableList(productList);
    }
}
