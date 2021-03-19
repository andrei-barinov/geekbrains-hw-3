package ru.geekbrains.hw_6;

import lombok.Data;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Data
public class EntityManagerClass {
    private EntityManagerFactory factory = new Configuration()
            .configure("hibernate.xml")
            .buildSessionFactory();

    private EntityManager em = factory.createEntityManager();

}
