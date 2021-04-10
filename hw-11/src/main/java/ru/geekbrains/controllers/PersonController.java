package ru.geekbrains.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.dto.PersonDTO;
import ru.geekbrains.entity.Person;
import ru.geekbrains.exception.PersonNotFoundException;
import ru.geekbrains.services.PersonService;
import ru.geekbrains.services.RoleService;


@RestController
@RequestMapping("/persons")
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;
    private final RoleService roleService;

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
        return personService.findPersonById(id).orElseThrow(() -> new PersonNotFoundException(
                String.format("Не найден человек с индитификатором %s", id)
        ));
    }

    @PostMapping("/user_service")
    public Person saveNewPerson(@RequestBody PersonDTO personDTO) {

        String nameRole = "ROLE_" + personDTO.getRole()
                .replace("rA", "r_A")
                .toUpperCase();

        Person person = new Person(
                personDTO.getPersonName(),
                roleService.findRoleByName(nameRole),
                personDTO.getLogin(),
                personDTO.getPassword()
        );

        return personService.saveOrUpdate(person);
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Person updatePerson(@RequestBody Person person) {
        return personService.saveOrUpdate(person);
    }

    @DeleteMapping("/{id}")
    public int deleteProduct(@PathVariable Long id) {
        personService.deletePersonById(id);
        return HttpStatus.OK.value();
    }
}
