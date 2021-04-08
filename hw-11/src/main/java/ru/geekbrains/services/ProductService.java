package ru.geekbrains.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.geekbrains.entity.Product;
import ru.geekbrains.repositories.ProductRepository;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Optional<Product> findProductById(Long id){
        return productRepository.findById(id);
    }

    public Product saveOrUpdate(Product product){
        return productRepository.save(product);
    }


    public void deleteProductById(Long id){
        productRepository.deleteById(id);
    }

    public Page<Product> findAll(int page){
        return productRepository.findAll(PageRequest.of(page-1, 10));
    }

}
