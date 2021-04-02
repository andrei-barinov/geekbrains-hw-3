package ru.geekbrains.entity;


import org.springframework.stereotype.Component;
import ru.geekbrains.exception.NotFoundException;
import ru.geekbrains.exception.PersonNotFoundException;
import ru.geekbrains.exception.ProductNotFoundException;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Cart {
    private List<Product> list;
    private Map<Long, List<Product>> cartList;

    @PostConstruct
    public void init(){
        this.list = new ArrayList<>();
        this.list.add(new Product(1L, "Book", 40));
        this.list.add(new Product(2L, "Computer", 100));
        this.list.add(new Product(3L, "Cake", 5));
        cartList = new HashMap<>();
        cartList.put(1L, list);
    }

    //Содержимое корзины
    public Map<Long, List<Product>> showCart(){
        return cartList;
    }

    //Товары конкретного потребителя
    public List<Product> getProductsOfPerson(Long idPerson){
        if(!this.cartList.containsKey(idPerson)){
            throw new PersonNotFoundException(
                    String.format("В корзине нет заказов у человека с индитификатором %s", idPerson));
        } else{
            return this.cartList.get(idPerson);
        }
    }

    //Добавить товар в корзину
    public void addItem(Long id, Product product){
        if(this.cartList.containsKey(id)){
            for(Long key: this.cartList.keySet()){
                if(key == id){
                    List<Product> productList = this.cartList.get(id);
                    productList.add(product);
                    this.cartList.replace(id, productList);
                }
            }
        } else {
            List<Product> productList = new ArrayList<>();
            productList.add(product);
            this.cartList.put(id, productList);
        }
    }

    //Удалить товар из корзины
    public void deleteItem(Long idPerson, Long idProduct){
            if(!this.cartList.containsKey(idPerson)){
                throw new PersonNotFoundException(
                        String.format("В корзине нет заказов у человека с индитификатором %s", idPerson));
            } else {
                List<Product> productList = this.cartList.get(idPerson);
                productList.stream()
                        .filter(x -> x.getId().equals(idProduct))
                        .findFirst()
                        .map(productList::remove)
                        .orElseThrow(() -> new ProductNotFoundException(
                                String.format("У данного пользователя не найден товар с индитификатором %s", idProduct)));
                this.cartList.replace(idPerson, productList);
            }
        }

    //Очистить корзину
    public void deleteAll(){
        for(Long key: this.cartList.keySet()){
            this.cartList.remove(key);
        }
    }

}
