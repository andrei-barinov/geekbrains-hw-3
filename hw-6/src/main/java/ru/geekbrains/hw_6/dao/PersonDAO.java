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
import java.util.Optional;

@Component("personDAO")
public class PersonDAO {
    private EntityManager em = (new EntityManagerClass()).getEm();

    public Optional<Person> findById(Long id){
        try{
            Person person = (Person) em.createQuery("select p from Person p where p.id = :id", Person.class)
                    .setParameter("id", id)
                    .getSingleResult();
            return Optional.of(person);
        } catch (NoResultException ex){
            return Optional.ofNullable(null);
        }
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
        if(findById(id).isPresent()){
            em.remove(findById(id).get());
            em.getTransaction().commit();
            System.out.println("Информация о человеке успешно удалена из БД");
        } else {
            System.out.println("Человека с таким id нет в БД");
        }

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
