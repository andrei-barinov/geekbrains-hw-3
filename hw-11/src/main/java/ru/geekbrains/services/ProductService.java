package ru.geekbrains.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.dto.ProductDTO;
import ru.geekbrains.entity.Product;
import ru.geekbrains.repositories.ProductRepository;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Optional<Product> findProductById(Long id) {
        return productRepository.findById(id);
    }

    public Optional<ProductDTO> findProductDtoById(Long id){
        return productRepository.findById(id).map(ProductDTO::new);
    }

    public Product saveOrUpdate(Product product){
        if(productRepository.findProductByTitle(product.getTitle()).isPresent()){
            productRepository.setNewPrice(product.getPrice(), product.getTitle());
            return product;
        }
        else {
            return productRepository.save(product);
        }
    }

    public void deleteProductById(Long id){
        productRepository.deleteById(id);
    }

    public Page<ProductDTO> findAll(Specification<Product> spec, int page, int pageSize) {
        return productRepository.findAll(spec, PageRequest.of(page - 1, pageSize)).map(ProductDTO::new);
    }



}
