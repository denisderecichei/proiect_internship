import model.*;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import utils.Services;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/**
 * Created by Denis Derecichei on 7/22/2016.
 */
public class testServices {
    private static User user1;
    private static User user2;
    private static User user3;
    private static Manager manager;
    private static Product product1;
    private static Product product2;
    private static Order order;
    private static Order order1;
    private static Order mockOrder;
    private static Services serviceMock;
    private static List<Product> productList;
    private static List<User> userList;
    private static Services services = new Services();

    @BeforeClass
    public static void initialSetup() {
        user1 = new User("jdoe", "123456", "jdoe");
        user2 = new User("denis", "123456", "denis");
        user3 = new User("joe", "123456", "joe");
        manager = new Manager("manager", "manager", "Admin");
        order = user1.createOrder("motilor", true);
        order1 = user2.createOrder("wha", false);
        product1 = new Product(15, "americanBurger", 15);
        product2 = new Product(5, "coca-cola", 0);
        productList = services.initialiseProducts();
        userList = services.initialiseManagement();
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        productList.add(product1);
        productList.add(product2);
        order.addProduct(services.searchProductByName(productList, "americanBurger"));
        order1.addProduct(services.searchProductByName(productList, "coca-cola"));
        order1.addProduct(services.searchProductByName(productList, "americanBurger"));
        order1.addProduct(services.searchProductByName(productList, "steak"));
        mockOrder = Mockito.mock(Order.class);
        serviceMock = Mockito.mock(Services.class);
    }

    @Test
    public void TestLogin() {
        User spyUser1 = Mockito.spy(user1);
        assertThat("Cannot perform login", services.login(userList, spyUser1.getUsername(), spyUser1.getPassword()), is(user1));
        User spyUser2 = Mockito.spy(user2);
        assertThat("Cannot perform login", services.login(userList, spyUser2.getUsername(), spyUser2.getPassword()), is(user2));
        User spyUser3 = Mockito.spy(user3);
        assertThat("Cannot perform login", services.login(userList, spyUser3.getUsername(), spyUser3.getPassword()), is(user3));
    }

    @Test
    public void TestProductSearch() {
        Product spyProduct1 = Mockito.spy(product1);
        Product spyProduct2 = Mockito.spy(product2);
        assertThat("Product not found", services.searchProductByName(productList, spyProduct2.getName()), is(product2));
        assertThat("Product not found", services.searchProductByName(productList, spyProduct1.getName()), is(product1));
    }

    @Test
    public void TestComputeFinishTime() {
        assertThat("FINISH TIME ERROR", services.computeFinishTime(order), is(15));
        assertThat("FINISH TIME ERROR", services.computeFinishTime(order1), is(17));

        doReturn(1000).when(serviceMock).computeDeliveryCost(mockOrder);
        assertThat("aaaaaaaaaa", serviceMock.computeDeliveryCost(mockOrder), is(1000));
    }

    @Test(expected = NullPointerException.class)
    public void TestComputeDeliveryCost() {
        assertThat("DELIVERY COST ERR0R", services.computeDeliveryCost(order), is(18));
        assertThat("DELIVERY COST ERR0R", services.computeDeliveryCost(order1), is(50));
        serviceMock.computeDeliveryCost(mockOrder);
        verify(serviceMock, times(1)).computeDeliveryCost(mockOrder);
        verify(mockOrder, never()).addProduct(product1);
        doThrow(NullPointerException.class).when(mockOrder).addProduct(null);
        mockOrder.addProduct(null);
    }

    @Test
    public void TestMocks() {
        Order mock = Mockito.mock(Order.class);
        mock.addProduct(product1);
        mock.getFinishTime();
        verify(mock, times(1)).addProduct(product1);
        verify(mock, atMost(4)).getFinishTime();
        verify(mock, never()).getAddress();
        when(mock.getFinishTime()).thenReturn(30);
        doReturn("home").when(mock).getAddress();
        assertThat("error", mock.getFinishTime(), is(30));
    }

    @Test
    public void TestUserBehaviour() {
        Order secondSpyOrder = Mockito.spy(order);
        manager.confirmOrder(secondSpyOrder);
        assertThat("Confirm error", secondSpyOrder.getOrderStatus(), is(OrderStatus.CONFIRMED));
        manager.readyOrder(secondSpyOrder);
        assertThat("Ready error", secondSpyOrder.getOrderStatus(), is(OrderStatus.READY));
        assertThat("Not ready error", secondSpyOrder.getFinishTime(), is(0));
        user1.pickUpOrder(secondSpyOrder);
        assertThat("Picking error", secondSpyOrder.getOrderStatus(), is(OrderStatus.PICKED));
        verify(secondSpyOrder, never()).getAddress();
        verify(secondSpyOrder, atLeast(2)).getOrderStatus();
        verify(secondSpyOrder, atMost(7)).getOrderStatus();
    }

    @Test(expected = NullPointerException.class)
    public void TestNPEThrow() {
        doReturn("www").when(mockOrder).getAddress();
        assertThat("Some error", mockOrder.getAddress(), is("www"));
        doReturn(null).when(serviceMock).initialiseManagement();
        when(serviceMock.initialiseManagement()).thenThrow(NullPointerException.class);
        serviceMock.initialiseManagement();
    }

    @Test
    public void TestDoWhenThen() {
        doReturn(10).when(mockOrder).getFinishTime();
        int testBool = mockOrder.getFinishTime();
        assertThat("not the same", testBool, is(10));

        when(mockOrder.getUser()).thenReturn(user1);
        assertThat("mismatch in username", mockOrder.getUser().getUsername(), is("jdoe"));

        doCallRealMethod().when(mockOrder).getAddress();
        assertThat("null", mockOrder.getAddress(), is(nullValue()));

        when(serviceMock.initialiseProducts()).thenReturn(new ArrayList<>());
        assertThat("should be 0", serviceMock.initialiseProducts().size(), is(0));


    }
}