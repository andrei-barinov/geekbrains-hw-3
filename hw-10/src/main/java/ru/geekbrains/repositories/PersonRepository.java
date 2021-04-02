package ru.geekbrains.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
