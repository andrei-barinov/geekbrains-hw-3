package ru.geekbrains.hw8.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.hw8.model.Product;
import ru.geekbrains.hw8.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<Product> findAllProducts(){
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public  Product findProductById(@PathVariable Long id){
        return productService.findProductById(id).get();
    }

    @DeleteMapping
    public void deleteProductById(@RequestParam Long id){
        productService.deleteProductById(id);
    }

    @PostMapping
    public Product saveNewProduct(@RequestBody Product product){
        return productService.saveOrUpdate(product);
    }

}
