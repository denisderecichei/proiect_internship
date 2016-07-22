package model;

import utils.Services;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private OrderStatus orderStatus;
    private User user;
    private String address;
    private boolean delivery;
    private List<Product> productList;
    private int finishTime;

    public Order(User user, String address, boolean delivery) {
        this.orderStatus = OrderStatus.NEW;
        this.user = user;
        this.address = address;
        this.delivery = delivery;
        this.productList = new ArrayList<>();
        System.out.println(" with status " + this.orderStatus);
    }

    public Order() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isDelivery() {
        return delivery;
    }

    public void setDelivery(boolean delivery) {
        this.delivery = delivery;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public void addProduct(Product product) {
        if (product == null) {

        } else {
            System.out.println(user.getUsername() + " added product " + product.getName());
            productList.add(product);
        }
    }

    public int getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Order order) {
        Services services=new Services();
        finishTime = services.computeFinishTime(order);
    }

    public void setFinishTime(Order order, int time) {
        finishTime = time;
    }
}
