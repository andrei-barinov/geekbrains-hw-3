package ru.geekbrains;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class FillRepository {
    private ProductRepository repository;

    @Autowired
    public FillRepository(ProductRepository repository) {
        this.repository = repository;
    }


    @PostConstruct
    private void postConstruct(){
        for (int i = 1; i <= 5; i++){
            repository.addProduct(new Product(i, "product-" + i, (i+1) * 10));
        }
    }

    public Product getProduct(){
        int max = 5;
        return repository.getProductList().get((int) Math.random() * ++max);
    }

}
