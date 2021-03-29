package ru.geekbrains.hw8.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    public Page<Product> findAllProducts(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "p", defaultValue = "1") Integer page
    ){
        if(page < 1){
            page = 1;
        }
        return productService.findAll(page);
    }

    @GetMapping("/{id}")
    public  Product findProductById(@PathVariable Long id){
        return productService.findProductById(id).get();
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id){
        productService.deleteProductById(id);
    }

    @PostMapping
    public Product saveNewProduct(@RequestBody Product product){
        return productService.saveOrUpdate(product);
    }

}
