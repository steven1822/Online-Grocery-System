import org.junit.Test;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import java.io.IOException;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class ServiceTest {
    public ServiceTest() { }
    
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


    


    @Test
    public void testAddItem() {
        Service.addItem(apple);

        assertEquals(1, Service.Items.size());
        assertEquals(apple, Service.Items.get(0));

    }

    @Test
    public void testAddOrder() {
        Service.addOrder(Service.order);
    }

    @Test
    public void testGetItem() {
        Service.addItem(apple);
        assertEquals(apple, Service.getItem("apple"));
    }

    @Test
    public void testGetOrder() throws IOException {
        Service.addOrder(Service.order);
        assertEquals(Service.order, Service.getOrder(1234567));
    }

    @Test
    public void testRemoveItem() {
        Service.addItem(apple);
        int size = Service.Items.size();
        Service.removeItem(apple);
        assertEquals(size-1, Service.Items.size());
    }

    @Test
    public void testRemoveOrder() {
        Service.removeOrder(Service.order);
        assertEquals(0, Service.Orders.size());
    }

    @Test
    public void testUpdateOrder() {
        ArrayList<Item> Items = new ArrayList<Item>();
        Items.add(apple);
        Service.order.setItems(Items);
        Service.updateOrder(Service.order);
        assertEquals(1.99f, Service.order.getTotalPrice(),2);
        assertEquals(1, Service.order.getSize());
    }
}
