package ru.geekbrains.hw_6.entity;

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

    @ManyToMany
    @JoinTable(name = "product_person",
            joinColumns = @JoinColumn(name ="person_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

    public Person(String personName) {
        this.personName = personName;
    }

    @Override
    public String toString() {
        return String.format("%s", getPersonName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id) && Objects.equals(personName, person.personName) && Objects.equals(products, person.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, personName, products);
    }
}
