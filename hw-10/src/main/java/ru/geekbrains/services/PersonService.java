package ru.geekbrains.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.entity.Person;
import ru.geekbrains.repositories.PersonRepository;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;

    public Optional<Person> findProductById(Long id){
        return personRepository.findById(id);
    }

    public List<Person> findAll(){
        return personRepository.findAll();
    }

    public Person saveOrUpdate(Person person){
        return personRepository.save(person);
    }

    public void deleteProductById(Long id){
        personRepository.deleteById(id);
    }
}
