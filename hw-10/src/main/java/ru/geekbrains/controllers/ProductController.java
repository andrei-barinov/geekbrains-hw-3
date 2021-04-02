package ru.geekbrains.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.entity.Product;
import ru.geekbrains.exception.ProductNotFoundException;
import ru.geekbrains.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{id}")
    @ApiOperation("Получить товар по id")
    public Product getProductById(@PathVariable Long id){
        return productService.findProductById(id).orElseThrow(() -> new ProductNotFoundException(
                String.format("Не найден товар с индитификатором %s", id)
        ));
    }

    @ApiOperation("Получить весь список товаров")
    @GetMapping
    public List<Product> getAllProducts(){
        return productService.findAll();
    }

    @ApiOperation("Довить новый товар в список товаров")
    @PostMapping
    public Product saveNewProduct(@RequestBody Product product){
        return productService.saveOrUpdate(product);
    }

    @ApiOperation("Обновить информацию о товаре в списке")
    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Product updateProduct(@RequestBody Product product){
        return productService.saveOrUpdate(product);
    }

    @ApiOperation("Удалить товар по id из списка товаров")
    @DeleteMapping("/{id}")
    public int deleteProduct(@PathVariable Long id){
        productService.deleteProductById(id);
        return HttpStatus.OK.value();
    }



}
