package model;

public class Manager extends User {

    public Manager(String username, String password, String name) {
        super(username, password, name);
    }

    @Override
    public void greeting(String username){
        System.out.println("Manager " + username +" was created");
    }
    public void confirmOrder(Order order){
        order.setOrderStatus(OrderStatus.CONFIRMED);
        System.out.println("Order is set to " + order.getOrderStatus() + "so the restaurant is aware of the order and is preparing it");
    }
    public void readyOrder(Order order) {
        order.setFinishTime(order, 0);
        order.setOrderStatus(OrderStatus.READY);
        System.out.println("Order is set to " + order.getOrderStatus() + ", waiting time " + order.getFinishTime() + " thus it can be picked up");
    }
}
