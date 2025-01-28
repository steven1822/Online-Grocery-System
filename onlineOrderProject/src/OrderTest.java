import org.junit.Test;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import java.io.IOException;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class OrderTest {

    public OrderTest() throws IOException { }
    
    @BeforeClass
    public static void setUpClass() { }
    
    @AfterClass
    public static void tearDownClass() { }
    
    @Before
    public void setUp() { 
        
    }
    
    @After
    public void tearDown() { }
    private Item apple = new Item("apple", 1.99f, 5, 0.40);
    private Item shirt = new Item("shirt", 15.99f,5,1.2);
    private Item tv = new Item("Tv", 305.99f, 2, 10.56);
    private ArrayList<Item> Items = new ArrayList<Item>();
    private Order order = new Order("Steven Miller", Items, Service.today);


    @Test
    public void testGetCustomerName() {
        assertEquals("Steven Miller" , order.getCustomerName());
    }

    @Test
    public void testGetItems() {
        assertEquals(Items, order.getItems());
    }

    @Test
    public void testGetOrderNumber() {
        assertEquals(1234567,order.getOrderNumber());

    }

    @Test
    public void testGetPickupDate() {
        assertEquals("2/18/24",order.getPickupDate());

    }


    
    @Test
    public void testGetSize() throws IOException {
        Items.add(apple);
        Items.add(shirt);
        Items.add(tv);
        Order order2 = new Order("Test", Items, Service.today);
        assertEquals(3,order2.getSize());
    }

    @Test
    public void testGetTotalPrice() throws IOException {
        Items.add(apple);
        Items.add(shirt);
        Items.add(tv);
        Order order2 = new Order("Test", Items, Service.today);
        assertEquals(323.97,order2.getTotalPrice(),2);
    }

    @Test
    public void testSetCustomerName() {
        order.setCustomerName("Brad Pitt");
        assertEquals("Brad Pitt",order.getCustomerName());
    }

    @Test
    public void testSetItems() {
        order.setItems(Items);
        assertEquals(Items, Items);
    }

    @Test
    public void testSetOrderNumber() {
        order.setOrderNumber(1234);
        assertEquals(1234, order.getOrderNumber());
    }

    @Test
    public void testSetPickupDate() {
        order.setPickupDate(Service.today);
        assertEquals("2/18/25", order.getPickupDate());
    }


    @Test
    public void testUpdateSize() throws IOException {
        Order order2 = new Order("test", Items,Service.today);
        Items.add(apple);
        order2.setItems(Items);
        order2.updateSize();
        assertEquals(1, order2.getSize());

    }

    @Test
    public void testUpdateTotalPrice() throws IOException {
        Order order2 = new Order("test", Items,Service.today);
        Items.add(apple);
        order2.setItems(Items);
        order2.updateTotalPrice();
        assertEquals(1.99, order2.getTotalPrice(),2);
    }
}
