package ru.geekbrains.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.entity.Person;
import ru.geekbrains.exception.PersonNotFoundException;
import ru.geekbrains.services.PersonService;

import java.util.List;

@RestController
@RequestMapping("/persons")
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;

    @GetMapping("/{id}")
    public Person getProductById(@PathVariable Long id) {
        return personService.findProductById(id).orElseThrow(() -> new PersonNotFoundException(
                String.format("Не найден человек с индитификатором %s", id)
        ));
    }

    @GetMapping
    public List<Person> getAllProducts() {
        return personService.findAll();
    }

    @PostMapping
    public Person saveNewProduct(@RequestBody Person person) {
        return personService.saveOrUpdate(person);
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Person updatePerson(@RequestBody Person person) {
        return personService.saveOrUpdate(person);
    }

    @DeleteMapping("/{id}")
    public int deleteProduct(@PathVariable Long id) {
        personService.deleteProductById(id);
        return HttpStatus.OK.value();
    }
}
