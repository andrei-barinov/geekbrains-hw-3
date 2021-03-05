package ru.geekbrains.print;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("cart")
public class Cart{
    private List<Product> cart;

    public Cart(List<Product> cart) {
        this.cart = cart;
    }

    public void getInformation() {
        for(Product p: cart){
            System.out.println(p.getId() + " " + p.getName() + " " + p.getPrice());
        }
    }

    public void addToCart(Product product) {
        cart.add(product);
    }

    public void deleteFromCart(Product product) {
        cart.remove(product);
    }
}
