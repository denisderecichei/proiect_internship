package model;

public class User {

    protected String username;
    protected String password;
    protected String name;

    public User(String username, String password, String name){
        this.username=username;
        this.password=password;
        this.name=name;
        greeting(username);
    }

    public void greeting(String username) {
        System.out.println("User "+ username +" was created");
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void pickUpOrder(Order order) {
        if(order.getOrderStatus().equals(OrderStatus.READY)&& order.getFinishTime()==0)  {
            order.setOrderStatus(OrderStatus.PICKED);
            System.out.println("Order is set to " + order.getOrderStatus()+ ", client has picked up the order");
        } else {
            System.out.println("Order is not ready later, please return later");
        }
    }

    public Order createOrder(String address, boolean delivery) {
        System.out.print("Order creater by user "+ this.getUsername());
        return new Order(this, address, delivery);
    }

}
