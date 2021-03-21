package ru.geekbrains.boot.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.boot.model.Product;
import ru.geekbrains.boot.repositories.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product save(Product product){
        return productRepository.save(product);
    }

    public Product show(int id){
        return productRepository.show(id);
    }
}
