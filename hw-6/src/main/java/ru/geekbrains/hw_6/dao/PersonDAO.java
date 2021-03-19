package ru.geekbrains.hw_6.dao;

import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Component;
import ru.geekbrains.hw_6.EntityManagerClass;
import ru.geekbrains.hw_6.entity.Person;
import ru.geekbrains.hw_6.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import java.util.List;

@Component("personDAO")
public class PersonDAO {
    private EntityManager em = (new EntityManagerClass()).getEm();

    public Person findById(Long id){
        Person person = (Person) em.createQuery("select p from Person p where p.id = :id", Person.class)
                .setParameter("id", id)
                .getSingleResult();
        return person;
    }

    private boolean findByName(String name){
        Person person1;
        try {
            person1 = (Person) em.createQuery("select  p from Person p where p.personName = :name")
                    .setParameter("name", name)
                    .getSingleResult();
            return true;
        } catch (NoResultException ex){
            return false;
        }

    }

    public List<Person> findAll(){
        List<Person> productList = (List<Person>) em.createQuery("select p from Person p").getResultList();
        return productList;
    }

    public void deleteById(Long id){
        em.getTransaction().begin();
        em.remove(findById(id));
        em.getTransaction().commit();
        System.out.println("Объект успешно удален из БД");
    }

    public Person saveOrUpdate(Person person){
        em.getTransaction().begin();
        if(findByName(person.getPersonName())){
            System.out.println("Человек уже есть в БД");
        } else {
            em.persist(person);
        }
        em.getTransaction().commit();
        return person;
    }
}
