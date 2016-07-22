package model;

public class Delivery extends User {

    public Delivery(String username, String password, String name) {
        super(username, password, name);
    }

    @Override
    public void greeting(String username) {
        System.out.println("Delivery boy " + username + " was created");
    }

    public void deliverOrder(Order order) {
        if (order.getOrderStatus().equals(OrderStatus.READY) && order.getFinishTime() == 0) {
            order.setOrderStatus(OrderStatus.DELIVERED);
            System.out.println("Order is set to " + order.getOrderStatus() + ", thus the client has received his order");
        } else {
            System.out.println("Order is not yet ready to be delivered");
        }
    }
}
