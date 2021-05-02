package ru.geekbrains.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.entity.Order;

@NoArgsConstructor
@Data
public class OrderDTO {
    private Long id;
    private String username;
    private int totalPrice;
    private String creationDateTime;

    public OrderDTO(Order order) {
        this.id = order.getId();
        this.username = order.getOwner().getPersonName();
        this.totalPrice = order.getPrice();
        this.creationDateTime = order.getCreatedAt().toString();
    }
}
