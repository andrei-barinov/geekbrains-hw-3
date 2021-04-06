package ru.geekbrains.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@Data
@Entity
public class Person {

    @Id //Первичный ключ
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "name", length = 128)
    private String personName;

    @ManyToOne
    private Role role;
    private String login;
    private String password;

    public Person(String personName) {
        this.personName = personName;
    }

    @Override
    public String toString() {
        return String.format("%s", getPersonName());
    }

}
