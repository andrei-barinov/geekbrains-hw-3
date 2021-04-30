package ru.geekbrains.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.dto.OrderDTO;
import ru.geekbrains.entity.Person;
import ru.geekbrains.exception.PersonNotFoundException;
import ru.geekbrains.services.OrderService;
import ru.geekbrains.services.PersonService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final PersonService personService;

    @GetMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrderFromCart(Principal principal){
        Person person = personService.findByLogin(principal.getName())
                .orElseThrow(() -> new PersonNotFoundException(" Пользователь не найден"));

        orderService.createFromUserCart(person);
    }

    @GetMapping
    public List<OrderDTO> getCurrentPersonOrders(Principal principal){
        return orderService.findAllOrdersByOwnerName(personService
                .findByLogin(principal.getName())
                .get()
                .getPersonName())
                .stream()
                .map(OrderDTO::new)
                .collect(Collectors.toList());
    }
}
