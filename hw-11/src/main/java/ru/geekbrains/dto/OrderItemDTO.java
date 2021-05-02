package ru.geekbrains.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.entity.OrderItem;

@NoArgsConstructor
@Data
public class OrderItemDTO {
    private String productTitle;
    private int quantity;
    private int PricePerProduct;
    private int price;

    public OrderItemDTO(OrderItem orderItem){
        this.productTitle = orderItem.getProduct().getTitle();
        this.quantity = orderItem.getQuantity();
        this.PricePerProduct = orderItem.getPricePerProduct();
        this.price = orderItem.getPrice();
    }
}
