package ru.geekbrains.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.cfg.Configuration;
import ru.geekbrains.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDAO {

    private EntityManagerFactory factory = new Configuration()
            .configure("hibernate.xml")
            .buildSessionFactory();

    private EntityManager em = factory.createEntityManager();


    public Product findById(Long id){
        Product product = (Product) em.createQuery("select p from Product p where p.id = :id")
                .setParameter("id", id)
                .getSingleResult();
        return product;
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
        em.remove(findById(id));
        em.getTransaction().commit();
        System.out.println("Объект успешно удален из БД");
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
