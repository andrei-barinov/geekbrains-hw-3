package ru.geekbrains.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.dto.ProductDTO;
import ru.geekbrains.entity.Product;
import ru.geekbrains.exception.ProductNotFoundException;
import ru.geekbrains.repositories.specifications.ProductSpecifications;
import ru.geekbrains.services.ProductService;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public Page<ProductDTO> findAllProducts(
            @RequestParam MultiValueMap<String, String> params,
            @RequestParam(name = "p", defaultValue = "1") Integer page
    ){
        if(page < 1){
            page = 1;
        }
        return productService.findAll(ProductSpecifications.build(params), page, 5);
    }

    @GetMapping("/{id}")
    public  ProductDTO findProductById(@PathVariable Long id){
        return productService.findProductDtoById(id)
                .orElseThrow(() -> new ProductNotFoundException(
                        String.format("Не найден товар с индитификатором %s", id)));
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id){
        productService.deleteProductById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product saveNewProduct(@RequestBody Product product){
        return productService.saveOrUpdate(product);
    }

}
