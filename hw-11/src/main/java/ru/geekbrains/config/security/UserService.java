package ru.geekbrains.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.geekbrains.entity.Person;
import ru.geekbrains.repositories.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private PersonRepository personRepository;

    private static Person thisPerson;

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
