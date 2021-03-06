package ru.geekbrains.print;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductRepository {
    private List<Product> productRepository = new ArrayList<>();


    public List<Product> getProductRepository() {
        return productRepository;
    }

    public Product getProductFromRepo(String str){
        String[] data = str.split(" ");
        int id = Integer.parseInt(data[2]);
        for(Product p: this.getProductRepository()){
            if(p.getId()==id) return p;
        }
        return null;
    }

    public void add(Product product) {
        productRepository.add(product);
    }

    public void getInformation() {
        for(Product p: productRepository){
            System.out.println(p.getId() + " " + p.getName() + " " + p.getPrice());
        }
    }

    public void getInformation(String str) {
        String[] data = str.split(" ");
        int id = Integer.parseInt(data[1]);
        for(Product p: productRepository){
            if(p.getId()==id) System.out.println(p.getId() + " " + p.getName() + " " + p.getPrice());
        }
    }

    @Override
    public String toString() {
        return "ProductRepository{" +
                "productRepository=" + productRepository +
                '}';
    }
}
