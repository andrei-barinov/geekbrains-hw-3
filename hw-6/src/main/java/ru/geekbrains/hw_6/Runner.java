package ru.geekbrains.hw_6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ru.geekbrains.hw_6.entity.Person;
import ru.geekbrains.hw_6.entity.Product;
import ru.geekbrains.hw_6.services.Service;

@Component
public class Runner implements CommandLineRunner {
    private final Service service;

    @Autowired
    public Runner(Service service){
        this.service = service;
    };

    @Override
    public void run(String... args) throws Exception {
        System.out.println("---------------------------------------------------------------------------------");
        service.getListOfProductByIdPerson(1L).stream().forEach(Product::printProduct);
        System.out.println("_________________________________________________________________________________");
        System.out.println(service.getListOfPersonByIdProduct(11L));
        service.getInformationByIdPerson(1L);
    }
}
