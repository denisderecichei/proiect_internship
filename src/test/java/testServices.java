import model.Order;
import model.Product;
import model.User;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import utils.Services;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by Denis Derecichei on 7/22/2016.
 */
public class testServices {
    private static User user1;
    private static User user2;
    private static User user3;
    private static List<Product> productList;
    private static List<User> userList;
    private static Services services = new Services();

    @BeforeClass
    public static void initialSetup() {
        user1 = new User("jdoe", "123456", "jdoe");
        user2 = new User("denis", "123456", "denis");
        user3 = new User("joe", "123456", "joe");
        productList = services.initialiseProducts();
        userList = services.initialiseManagement();
    }

    @Test
    public void TestUserCreation() {
        Assert.assertThat("Username does not correspond", user1.getUsername(), is("jdoe"));
        Assert.assertThat("Password does not correspond", user1.getPassword(), is("123456"));
        Assert.assertThat("Name does not correspond", user1.getName(), is("jdoe"));
    }

    @Test
    public void TestMocks() {
        Order mock = Mockito.mock(Order.class);
        Order mock1 = Mockito.mock(Order.class);
        mock.addProduct(null);
        mock.getFinishTime();
        mock.getFinishTime();
        mock.getFinishTime();
        mock.getFinishTime();
        mock.getFinishTime();
        verify(mock, times(1)).addProduct(null);
        verify(mock, atLeast(4)).getFinishTime();
        verify(mock, never()).getAddress();
    }


    /*    @Test
        public void TestTest() {
            List<String> something=new ArrayList<>();
            List<String> spyList=Mockito.spy(something);
            spyList.add("a");
            spyList.add("b");
            spyList.add("c");
            spyList.add("d");
            spyList.add("e");
    //        System.out.println(spyList);
            Mockito.verify(spyList).add("e");
            Assert.assertThat("not matching", 5, is(spyList.size()));
        }*/
    @Spy
    List<String> spyList = new ArrayList<>();

    @Test
    public void whenUsingTheSpyAnnotation_thenObjectIsSpied() {
        spyList.add("one");
        spyList.add("two");

        Mockito.verify(spyList).add("one");
        Mockito.verify(spyList).add("two");

        assertEquals(2, spyList.size());
    }
}

