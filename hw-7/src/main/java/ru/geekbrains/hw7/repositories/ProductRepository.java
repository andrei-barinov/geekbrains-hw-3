package ru.geekbrains.hw7.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.hw7.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByPriceGreaterThan(int minPrice);
    List<Product> findAllByPriceLessThanEqual(int maxPrice);
    List<Product> findAllByPriceBetween(int minPrice, int maxPrice);
}
