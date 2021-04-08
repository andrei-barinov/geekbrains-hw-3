package ru.geekbrains.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

    @GetMapping
    public Page<Person> findAllPerson(
            @RequestParam(name = "title", required = false) String title,
            @RequestParam(name = "p", defaultValue = "1") Integer page
    ){
        if(page < 1){
            page = 1;
        }
        return personService.findAll(page);
    }


    @GetMapping("/{id}")
    public Person getProductById(@PathVariable Long id) {
        return personService.findProductById(id).orElseThrow(() -> new PersonNotFoundException(
                String.format("Не найден человек с индитификатором %s", id)
        ));
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
