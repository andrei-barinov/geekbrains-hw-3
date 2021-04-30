package ru.geekbrains.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.beans.Cart;
import ru.geekbrains.dto.CartDTO;


@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final Cart cart;

    @GetMapping
    public CartDTO getCart(){
        return new CartDTO(cart);
    }

    @GetMapping("/add/{id}")
    public void addToCart(@PathVariable Long id){
        cart.addToCart(id);
    }

    @GetMapping("/clear")
    public void clearCart(){
        cart.clear();
    }
}
