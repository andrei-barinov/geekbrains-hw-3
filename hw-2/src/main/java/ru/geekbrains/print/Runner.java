package ru.geekbrains.print;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class Runner implements CommandLineRunner {
    private final ProductRepository productRepository;
    private final Cart cart;

    public Runner(ProductRepository productRepository, Cart cart) {
        this.productRepository = productRepository;
        this.cart = cart;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Добавьте 5 товаров в список товаров.");
        setAddToProductRepository();
        while(true){
            System.out.println("Для получения информации о товарах введите -getInfo" + "\n" +
                    "Для управления корзиной введите -managementCart" + "\n" +
                    "Для выхода введите -exit");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String str = reader.readLine();
            if(str.equals("-getInfo")) getInformation();
            else if(str.equals("-managementCart")){
                managementCart();
            }
            else if(str.equals("-exit")) break;
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
        while (true){
            System.out.println("Введите: " + "\n" + "\"id [id товара]\", для получения информации о конкретном товаре" + "\n" +
                    "\"allList\" для получения всего списка товаров" + "\n" +
                    "-exit для выхода из метода");
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

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void managementCart(){
        while (true){
            System.out.println("Введите: " + "\n" + "-add \"id [id товара]\", для добавления товара в корзину" + "\n" +
                             "-delete \"id [id товара]\", для удаления товара из корзины" + "\n"+
                    "-getInfo, для получения списка товаров в корзине" + "\n"+
                    "-exit для выхода из метода");
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
                else if(str.startsWith("-delete")){
                    Product product = productRepository.getProductFromRepo(str);
                    if(product == null) System.out.println("Такого товара нет в корзине");
                    else cart.deleteFromCart(product);
                }
                else if(str.startsWith("-getInfo")) cart.getInformation();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
