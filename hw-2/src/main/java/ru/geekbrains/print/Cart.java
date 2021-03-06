package ru.geekbrains.print;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Scope("prototype")
@Component("cart")
public class Cart{
    private List<Product> cart = new ArrayList<Product>();

    public void getInformation() {
        if(cart.size()==0) System.out.println("В корзине нет товаров");
        else {
            for(Product p: cart){
                System.out.println(p.getId() + " " + p.getName() + " " + p.getPrice());
            }
        }
    }

    public void addToCart(Product product) {
        cart.add(product);
    }

    public void removeFromCart(Product product) {
        cart.remove(product);
    }
}
