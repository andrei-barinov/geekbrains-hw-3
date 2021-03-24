package ru.geekbrains.hw8.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public  Product findProductById(Long id){
        return productService.findProductById(id).get();
    }

}
