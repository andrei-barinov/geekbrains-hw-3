package ru.geekbrains.hw7.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.hw7.entity.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByPriceGreaterThanEqual(int minPrice);
    List<Product> findAllByPriceLessThanEqual(int maxPrice);
    List<Product> findAllByPriceBetween(int minPrice, int maxPrice);
}
