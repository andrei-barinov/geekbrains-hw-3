package ru.geekbrains.hw8.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.hw8.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
