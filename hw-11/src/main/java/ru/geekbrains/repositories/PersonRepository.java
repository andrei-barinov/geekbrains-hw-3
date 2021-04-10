package ru.geekbrains.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.geekbrains.entity.Person;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByLogin(String login);

    @Query("select p.password from Person p where p.login = :login")
    String findPasswordByLogin(String login);

    @Modifying
    @Transactional
    @Query("update Person p set p.personName = :name, p.role.id = :role, p.password = :password where p.login = :login")
    void setNewNameAndPasswordAndRole(String name, UUID role, String login, String password);
}
