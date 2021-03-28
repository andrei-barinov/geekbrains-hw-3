package ru.geekbrains.hw_6.services;

import org.springframework.stereotype.Component;
import ru.geekbrains.hw_6.EntityManagerClass;
import ru.geekbrains.hw_6.dao.PersonDAO;
import ru.geekbrains.hw_6.dao.ProductDAO;
import ru.geekbrains.hw_6.entity.Person;
import ru.geekbrains.hw_6.entity.Product;

import javax.persistence.EntityManager;
import java.util.*;

@Component("service")
public class Service {
    private EntityManager em = (new EntityManagerClass()).getEm();
    private final ProductDAO productDAO;
    private final PersonDAO personDAO;

    public Service(ProductDAO productDAO, PersonDAO personDAO) {
        this.productDAO = productDAO;
        this.personDAO = personDAO;
    }

    public void getInformationByIdPerson(Long id){
        if(personDAO.findById(id).isPresent()){
            String name = personDAO.findById(id).get().getPersonName();
            List<Product> productList = this.getListOfProductByIdPerson(id);
            if(productList.isEmpty()){
                System.out.println(name + " не совершал покупок");
            } else {
                for(int i =0; i < productList.size(); i++){
                    System.out.println("-----------------------");
                    System.out.println("Покупатель: " + name + "\n" + "Товар: " +
                            productList.get(i).getName() + "\n" + "Цена: " + productList.get(i).getPrice());
                    System.out.println("-----------------------");
                }
            }
        }
        else {
            System.out.println("Человека с таким id нет в БД");
        }

    }


    public List<Person> getListOfPersonByIdProduct(Long id){
        List<Person> personList = (List<Person>) em.createQuery("select person from Person person left join person.products product where product.id = :id", Person.class)
                .setParameter("id", id)
                .getResultList();
        Set<Person> set = new HashSet<Person>();
        personList.stream().forEach(person -> set.add(person));
        List<Person> list = new ArrayList<Person>(set);
        return list;
    }


    public List<Product> getListOfProductByIdPerson(Long id){
        List<Product> productList = (List<Product>) em.createQuery("select product from Product product left join product.persons person where person.id = :id", Product.class)
                .setParameter("id", id)
                .getResultList();
        return productList;
    }
}
