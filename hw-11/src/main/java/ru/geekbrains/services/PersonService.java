package ru.geekbrains.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.geekbrains.entity.Person;
import ru.geekbrains.repositories.PersonRepository;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonService implements UserDetailsService {
    private final PersonRepository personRepository;
    private static Person thisPerson;

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

    public Optional<Person> findByPersonName(String pesonName){
        return personRepository.findByPersonName(pesonName);
    }

    public Optional<Person> findByLogin(String login){

        return personRepository.findByLogin(login);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Person> optionalPerson = personRepository.findByLogin(s);
        if (optionalPerson.isEmpty()){
            throw new UsernameNotFoundException("Пользователь с таким логин не найден");
        }

        thisPerson = optionalPerson.get();

        Person person = optionalPerson.get();
        return new User(person.getLogin(), person.getPassword(), List.of(new SimpleGrantedAuthority(person.getRole().getName())));
    }

}
