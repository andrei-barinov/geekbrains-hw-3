package ru.geekbrains.hw9.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.hw9.entity.Product;
import ru.geekbrains.hw9.exceptions.ProductErrorResponse;
import ru.geekbrains.hw9.exceptions.ProductNotFoundException;
import ru.geekbrains.hw9.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{id}")
    public  Product getProductById(@PathVariable Long id){
        return productService.findProductById(id).get();
    }

    @GetMapping
    public List<Product> getAllProducts(){
        return productService.findAll();
    }

    @PostMapping
    public Product saveNewProduct(@RequestBody Product product){
        product.setId(0L);
        return productService.saveOrUpdate(product);
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Product updateProduct(@RequestBody Product product){
        return productService.saveOrUpdate(product);
    }

    @DeleteMapping("/{id}")
    public int deleteProduct(@PathVariable Long id){
        productService.deleteProductById(id);
        return HttpStatus.OK.value();
    }

    @ExceptionHandler
    public ResponseEntity<ProductErrorResponse> handleException(ProductNotFoundException exc) {
        ProductErrorResponse studentsErrorResponse = new ProductErrorResponse();
        studentsErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        studentsErrorResponse.setMessage(exc.getMessage());
        studentsErrorResponse.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(studentsErrorResponse, HttpStatus.NOT_FOUND);
    }


}
