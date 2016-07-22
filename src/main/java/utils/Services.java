package utils;

import model.*;

import java.util.ArrayList;
import java.util.List;

public class Services {
    public Services() {

    }

    public User login(User user, String username, String password) {

        if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
            if (user instanceof Manager) {
                System.out.println("Welcome " + user.getName() + ", manager");
                return user;
            } else if (user instanceof Delivery) {
                System.out.println("Welcome " + user.getName() + ", delivery");
                return user;
            } else {
                System.out.println("Welcome! " + user.getUsername() + " user logged in");
                return user;
            }
        }
        return null;
    }

    public User login(List<User> list, String username, String password) {
        for (User x : list) {
            if (login(x, username, password) != null) {
                return x;
            }
        }
        System.out.println("There is no such user");
        return null;

    }

    public Product searchProductByName(List<Product> products, String name) {
        for (Product x : products) {
            if (x.getName().equals(name)) {
                return x;
            }

        }
        System.out.println("product not found");
        return null;
    }

    public int computeFinishTime(Order order) {
        int time = 0;
        for (Product x : order.getProductList())
            time += x.getCookingTime();
        return time;
    }

    public int computeDeliveryCost(Order order) {
        int cost = 0;

        for (Product x : order.getProductList())
            cost += x.getPrice();

        if (!order.isDelivery()) {
            System.out.println("No delivery needed, client will pick up the order when ready");
            return cost;
        }
        if (cost > 20) {
            System.out.println("Order was over 20$, thus the shipping is free.");
            return cost;
        } else {
            System.out.println("Order was under 20$, so an extra 3$ will be added for delivery.");
            return cost + 3;
        }

    }

    public List<Product> initialiseProducts() {
        List<Product> productList = new ArrayList<>();
        Product product1 = new Product(3, "cola", 0);
        productList.add(product1);
        Product product2 = new Product(10, "burger", 10);
        productList.add(product2);
        Product product3 = new Product(5, "fries", 3);
        productList.add(product3);
        Product product4 = new Product(30, "steak", 2);
        productList.add(product4);
        Product product5 = new Product(25, "shrimp", 2);
        productList.add(product5);
        return productList;
    }

    public List<User> initialiseManagement() {
        List<User> userList = new ArrayList<User>();
        Manager manager = new Manager("admin", "admin", "Dan");
        Delivery delivery = new Delivery("deli", "very", "livrari");
        userList.add(manager);
        userList.add(delivery);
        return userList;

    }
}
