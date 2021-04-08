package ru.geekbrains.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.geekbrains.entity.Person;
import ru.geekbrains.entity.Product;
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

    public void save(Person person) {
        personRepository.save(person);
    }

    public Page<Person> findAll(int page){
        return personRepository.findAll(PageRequest.of(page-1, 10));
    }
}
