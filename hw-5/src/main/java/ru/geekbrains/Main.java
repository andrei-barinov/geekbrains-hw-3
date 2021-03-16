package ru.geekbrains;

import ru.geekbrains.dao.ProductDAO;
import ru.geekbrains.entity.Product;

public class Main {
    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO();
        productDAO.saveOrUpdate(new Product("Car", 150));
        System.out.println(productDAO.findById(1L).toString());
        productDAO.deleteById(8L);
        productDAO.findAll().stream().forEach(System.out::println);
    }
}
