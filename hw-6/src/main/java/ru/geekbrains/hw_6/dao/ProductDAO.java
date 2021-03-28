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

@Component("productDAO")
public class ProductDAO {
    private EntityManager em;

    public ProductDAO() {
        this.em = (new EntityManagerClass()).getEm();
    }

    public Optional<Product> findById(Long id){
        Product product = (Product) em.createQuery("select p from Product p where p.id = :id")
                .setParameter("id", id)
                .getSingleResult();
        return Optional.of(product);
    }

    private boolean findByName(String name){
        Product product1;
        try {
            product1 = (Product) em.createQuery("select  p from Product p where p.name = :name")
                    .setParameter("name", name)
                    .getSingleResult();
            return true;
        } catch (NoResultException ex){
            return false;
        }

    }

    public List<Product> findAll(){
        List<Product> productList = (List<Product>) em.createQuery("select p from Product p").getResultList();
        return productList;
    }

    public void deleteById(Long id){
        em.getTransaction().begin();
        if(findById(id).isPresent()){
            em.remove(findById(id));
            em.getTransaction().commit();
            System.out.println("Продукт успешно удален из БД");
        } else{
            System.out.println("Продукта с таким id нет в БД");
        }

    }

    public Product saveOrUpdate(Product product){
        em.getTransaction().begin();
        if(findByName(product.getName())){
            em.createQuery("update Product p set p.price= :price where p.name= :name")
                    .setParameter("price", product.getPrice())
                    .setParameter("name", product.getName())
                    .executeUpdate();
        } else {
            em.persist(product);
        }
        em.getTransaction().commit();
        return product;
    }
}
