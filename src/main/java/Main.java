import model.*;
import utils.Services;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Services services=new Services();
        List<User> listOfUsers= services.initialiseManagement();
        List<Product> productList= services.initialiseProducts();
        System.out.println();
        Manager manager= (Manager) services.login(listOfUsers, "admin", "admin");
        Delivery delivery= (Delivery) services.login(listOfUsers, "deli", "very");
        System.out.println();
        User jdoe=new User("jdoe", "sdl123", "John Doe");
        listOfUsers.add(jdoe);
        User currentClient= services.login(listOfUsers,"jdoe", "sdl123");

        System.out.println();
        Order order1=currentClient.createOrder("5th Avenue", false);
        order1.addProduct(services.searchProductByName(productList, "shrimp"));
        order1.addProduct(services.searchProductByName(productList, "fries"));
        order1.addProduct(services.searchProductByName(productList, "cola"));
        System.out.println("Time until order is ready: " + services.computeFinishTime(order1)+ " minutes");
        System.out.println("Total price: "+ services.computeDeliveryCost(order1)+"$");
        manager.confirmOrder(order1);
        currentClient.pickUpOrder(order1);
        manager.readyOrder(order1);
        currentClient.pickUpOrder(order1);

        System.out.println();
        Order order2=currentClient.createOrder("Bld. Unirii", true);
        order2.addProduct(services.searchProductByName(productList, "shrimp"));
        order2.addProduct(services.searchProductByName(productList, "fries"));
        order2.addProduct(services.searchProductByName(productList, "cola"));
        System.out.println("Time until order is ready: " + services.computeFinishTime(order2)+ " minutes");
        System.out.println("Total price: "+ services.computeDeliveryCost(order2)+"$");
        manager.confirmOrder(order2);
        delivery.deliverOrder(order2);
        manager.readyOrder(order2);
        delivery.deliverOrder(order2);

        System.out.println();
        Order order3=currentClient.createOrder("home",true);
        order3.addProduct(services.searchProductByName(productList, "fries"));
        order3.addProduct(services.searchProductByName(productList, "cola"));
        System.out.println("Time until order is ready: " + services.computeFinishTime(order3)+ " minutes");
        System.out.println("Total price: "+ services.computeDeliveryCost(order3)+"$");
        manager.confirmOrder(order3);
        manager.readyOrder(order3);
        delivery.deliverOrder(order3);


    }
}
