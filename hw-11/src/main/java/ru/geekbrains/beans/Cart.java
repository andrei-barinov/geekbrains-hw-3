package ru.geekbrains.beans;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.entity.OrderItem;
import ru.geekbrains.entity.Product;
import ru.geekbrains.exception.NotFoundException;
import ru.geekbrains.exception.PersonNotFoundException;
import ru.geekbrains.exception.ProductNotFoundException;
import ru.geekbrains.services.ProductService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Data
public class Cart {
    private List<OrderItem> items;

    private final ProductService productService;

    private int totalPrice;

    @PostConstruct
    public void init(){
        this.items = new ArrayList<>();
    }

    public void addToCart(Long id){
        for(OrderItem o: items){
            if(o.getProduct().getId().equals(id)){
                o.incrementQuantity();
                return;
            }
        }
        Product p =productService.findProductById(id).orElseThrow(() -> new ProductNotFoundException(
                "Не найден товар с индитификатором " + id + "(addToCart)"));
        OrderItem orderItem = new OrderItem(p);
        items.add(orderItem);
        recalculate();
    }

    public void recalculate(){
        totalPrice = 0;
        for(OrderItem o: items){
            totalPrice += o.getPrice();
        }
    }

}
