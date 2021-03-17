package ru.geekbrains.hw_6.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
public class Person {

    @Id //Первичный ключ
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "personName", length = 128)
    private String personName;

    public Person(String personName) {
        this.personName = personName;
    }

}
