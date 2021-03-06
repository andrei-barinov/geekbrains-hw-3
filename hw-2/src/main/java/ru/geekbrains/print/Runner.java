package ru.geekbrains.print;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class Runner implements CommandLineRunner {
    private final ProductRepository productRepository;
    @Autowired
    private ApplicationContext applicationContext;
    private Cart cart;

    public Runner(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Добавьте 5 товаров в список товаров.");
        setAddToProductRepository();
        while(true){
            System.out.println("Введите: " + "\n" + "-getInfo, для получения информации о товарах;" + "\n" +
                    "-manageCart для управления корзиной;" + "\n" +
                    "-exit для выхода.");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String str = reader.readLine();
            if(str.equals("-getInfo")) getInformation();
            else if(str.equals("-manageCart")){
                cart = (Cart) applicationContext.getBean("cart");
                manageCart();
            }
            else if(str.equals("-exit")) break;
            else System.out.println("Неизвестная команда");
        }
    }

    public void setAddToProductRepository(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите название товара и цену через пробел: ");
        for(int i = 1; i < 6; i++){
            try {
                String[] data = reader.readLine().split(" ");
                productRepository.add(new Product( i, data[0], data[1]));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void getInformation(){
        System.out.println("Введите: " + "\n" + "\"id [id товара]\", для получения информации о конкретном товаре;" + "\n" +
                "\"allList\" для получения всего списка товаров;" + "\n" +
                "-exit для выхода из метода.");
        while (true){
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                String str = reader.readLine();
                if(str.equals("allList")){
                    productRepository.getInformation();
                }
                else if(str.startsWith("-exit")){
                    break;
                }
                else if(str.startsWith("id")){
                    productRepository.getInformation(str);
                }
                else System.out.println("Неизвестная команда");

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void manageCart(){
        System.out.println("Введите: " + "\n" + "-add \"id [id товара]\", для добавления товара в корзину;" + "\n" +
                "-remove \"id [id товара]\", для удаления товара из корзины;" + "\n"+
                "-getInfo, для получения списка товаров в корзине;" + "\n"+
                "-exit для выхода из метода.");
        while (true){
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                String str = reader.readLine();
                if(str.startsWith("-add")){
                    Product product = productRepository.getProductFromRepo(str);
                    if(product == null) System.out.println("Такого товара нет в списке");
                    else{
                        cart.addToCart(product);
                        System.out.println(product.getName() + " добавлен в корзину");
                    }
                }
                else if(str.startsWith("-remove")){
                    Product product = productRepository.getProductFromRepo(str);
                    if(product == null) System.out.println("Такого товара нет в корзине");
                    else cart.removeFromCart(product);
                }
                else if(str.startsWith("-getInfo")) cart.getInformation();
                else if(str.startsWith("-exit")) break;
                else System.out.println("Неизвестная команда");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
