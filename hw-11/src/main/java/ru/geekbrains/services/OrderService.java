package ru.geekbrains.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.geekbrains.beans.Cart;
import ru.geekbrains.entity.Order;
import ru.geekbrains.entity.Person;
import ru.geekbrains.repositories.OrderRepository;
import ru.geekbrains.repositories.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final Cart cart;

    public Order createFromUserCart(Person person){
        Order order = new Order(cart, person);
        order = orderRepository.save(order);
        cart.clear();
        return order;
    }

    public List<Order> findAllOrdersByOwnerName(String ownerName){
        return orderRepository.findAllByOwner_PersonName(ownerName);
    }

}
