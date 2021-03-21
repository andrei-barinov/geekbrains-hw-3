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
    public List<Product> showAll(Model model) {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id){
        return productRepository.findById(id).get();
    }

    @GetMapping("/delete/{id}")
    public List<Product> deleteProductById(@PathVariable Long id){
        productRepository.deleteById(id);
        return productRepository.findAll();
    }

    @GetMapping("/search_by_min_price")
    public List<Product> searchByMinPrice(@RequestParam(name = "min_price")  int minPrice){
        return productRepository.findAllByPriceGreaterThan(minPrice);
    }

    @GetMapping("/search_by_max_price")
    public List<Product> searchByMaxPrice(@RequestParam(name = "max_price") int maxPrice){
        return productRepository.findAllByPriceLessThanEqual(maxPrice);
    }

    @GetMapping("/search_between")
    public List<Product> searchByPriceBetweenMinPriceAndMaxPrice(@RequestParam(name = "min_price") int minPrice,
                                                                 @RequestParam(name = "max_price") int maxPrice){
        return productRepository.findAllByPriceBetween(minPrice, maxPrice);
    }

    @PostMapping()
    public Product save(@RequestBody Product product){
        return productRepository.save(product);
    }
}
