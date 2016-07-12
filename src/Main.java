import model.*;
import utils.Services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<User> listOfUsers=Services.initialiseManagement();
        List<Product> productList=Services.initialiseProducts();
        System.out.println();
        Manager manager= (Manager) Services.login(listOfUsers, "admin", "admin");
        Delivery delivery= (Delivery) Services.login(listOfUsers, "deli", "very");
        System.out.println();
        User jdoe=new User("jdoe", "sdl123", "John Doe");
        listOfUsers.add(jdoe);
        User currentClient=Services.login(listOfUsers,"jdoe", "sdl123");

        System.out.println();
        Order order1=currentClient.createOrder("5th Avenue", false);
        order1.addProduct(Services.searchProductByName(productList, "shrimp"));
        order1.addProduct(Services.searchProductByName(productList, "fries"));
        order1.addProduct(Services.searchProductByName(productList, "cola"));
        System.out.println("Time until order is ready: " + Services.computeFinishTime(order1)+ " minutes");
        System.out.println("Total price: "+ Services.computeDeliveryCost(order1)+"$");
        manager.confirmOrder(order1);
        currentClient.pickUpOrder(order1);
        manager.readyOrder(order1);
        currentClient.pickUpOrder(order1);

        System.out.println();
        Order order2=currentClient.createOrder("Bld. Unirii", true);
        order2.addProduct(Services.searchProductByName(productList, "shrimp"));
        order2.addProduct(Services.searchProductByName(productList, "fries"));
        order2.addProduct(Services.searchProductByName(productList, "cola"));
        System.out.println("Time until order is ready: " + Services.computeFinishTime(order2)+ " minutes");
        System.out.println("Total price: "+ Services.computeDeliveryCost(order2)+"$");
        manager.confirmOrder(order2);
        delivery.deliverOrder(order2);
        manager.readyOrder(order2);
        delivery.deliverOrder(order2);

        System.out.println();
        Order order3=currentClient.createOrder("home",true);
        order3.addProduct(Services.searchProductByName(productList, "fries"));
        order3.addProduct(Services.searchProductByName(productList, "cola"));
        System.out.println("Time until order is ready: " + Services.computeFinishTime(order3)+ " minutes");
        System.out.println("Total price: "+ Services.computeDeliveryCost(order3)+"$");
        manager.confirmOrder(order3);
        manager.readyOrder(order3);
        delivery.deliverOrder(order3);

	System.out.println("1");
	System.out.println("2");


    }
}
