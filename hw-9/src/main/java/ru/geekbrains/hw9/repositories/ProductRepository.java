package ru.geekbrains.hw9.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.hw9.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
