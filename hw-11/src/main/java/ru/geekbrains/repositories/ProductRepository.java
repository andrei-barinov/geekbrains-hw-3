package ru.geekbrains.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.geekbrains.entity.Product;

import javax.transaction.Transactional;
import java.util.Optional;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    @Modifying
    @Transactional
    @Query("update Product p set p.price = ?1 where p.title = ?2")
    void setNewPrice(Integer price, String title);

    Optional<Product> findProductByTitle(String title);
}
