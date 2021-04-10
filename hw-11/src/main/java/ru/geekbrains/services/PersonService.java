package ru.geekbrains.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.geekbrains.entity.Person;
import ru.geekbrains.repositories.PersonRepository;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Optional<Person> findPersonById(Long id){
        return personRepository.findById(id);
    }

    public List<Person> findAll(){
        return personRepository.findAll();
    }

    public Person saveOrUpdate(Person person){
        person.setPassword(bCryptPasswordEncoder.encode(person.getPassword()));

        if(personRepository.findByLogin(person.getLogin()).isPresent()){
            personRepository.setNewNameAndPasswordAndRole(
                    person.getPersonName(),
                    person.getRole().getId(),
                    person.getLogin(),
                    person.getPassword()
            );
            return person;
        }else {
            return personRepository.save(person);
        }
    }

    public void deletePersonById(Long id){
        personRepository.deleteById(id);
    }

    public Page<Person> findAll(int page){
        return personRepository.findAll(PageRequest.of(page-1, 10));
    }

}
