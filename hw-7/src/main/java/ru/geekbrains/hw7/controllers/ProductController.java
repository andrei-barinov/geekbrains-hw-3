package ru.geekbrains.hw7.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.hw7.entity.Product;
import ru.geekbrains.hw7.repositories.ProductRepository;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductRepository productRepository;

    @GetMapping
    public List<Product> searchByPriceBetweenMinPriceAndMaxPrice(@RequestParam(required = false, name = "min_price") Integer minPrice,
                                                                 @RequestParam(required = false, name = "max_price") Integer maxPrice){
        if(minPrice != null && maxPrice != null){
            return productRepository.findAllByPriceBetween(minPrice, maxPrice);
        }
        else if(minPrice != null){
            return productRepository.findAllByPriceGreaterThanEqual(minPrice);
        }
        else if(maxPrice != null){
            return productRepository.findAllByPriceLessThanEqual(maxPrice);
        }
        return productRepository.findAll();
    }

    @GetMapping("/delete/{id}")
    public void deleteProductById(@PathVariable Long id){
        productRepository.deleteById(id);
    }


    @PostMapping()
    public Product save(@RequestBody Product product){
        return productRepository.save(product);
    }
}
