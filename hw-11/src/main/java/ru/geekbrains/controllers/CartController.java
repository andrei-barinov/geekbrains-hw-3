package ru.geekbrains.controllers;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.entity.Cart;
import ru.geekbrains.entity.Product;
import ru.geekbrains.exception.PersonNotFoundException;
import ru.geekbrains.exception.ProductNotFoundException;
import ru.geekbrains.repositories.PersonRepository;
import ru.geekbrains.services.ProductService;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final Cart cart;
    private final ProductService productService;
    private final PersonRepository personRepository;

    @ApiOperation("Узнать содержимое корзины")
    @GetMapping
    public Map<Long, List<Product>> showCart(){
        return cart.showCart();
    }

    @ApiOperation("Вывести товары конкретного потребителя")
    @GetMapping("/goods")
    public List<Product> getProductOfPerson(@RequestParam(name = "person") Long idPerson){
        return cart.getProductsOfPerson(idPerson);
    }

    @ApiOperation("Добавить товар в корзину по id пользователя и id товара")
    @GetMapping("/add")
    public int addProduct(@RequestParam(name = "person") Long idPerson,
                            @RequestParam(name = "product") Long idProduct){
        personRepository.findById(idPerson).orElseThrow(() -> new PersonNotFoundException(
                String.format("Не найден человек с индитификатором %s", idPerson)));

        Product product = productService.findProductById(idProduct).orElseThrow(() -> new ProductNotFoundException(
                String.format("Не найден товар с индитификатором %s", idProduct)));

        cart.addItem(idPerson, product);
        return HttpStatus.OK.value();
    }

    @ApiOperation("Удалить товар из корзины по id пользователя и id товара")
    @DeleteMapping()
    public int deleteProduct(@RequestParam(name = "person") Long idPerson,
                             @RequestParam(name = "product") Long idProduct){
        cart.deleteItem(idPerson, idProduct);
        return HttpStatus.OK.value();
    }

    @ApiOperation("Очистить корзину")
    @DeleteMapping("/delete_all")
    public int deleteAll(){
        cart.deleteAll();
        return  HttpStatus.OK.value();
    }
}
