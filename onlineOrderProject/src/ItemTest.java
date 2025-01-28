import org.junit.Test;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;

public class ItemTest {
    public ItemTest() { }
    
    @BeforeClass
    public static void setUpClass() { }
    
    @AfterClass
    public static void tearDownClass() { }
    
    @Before
    public void setUp() { 
        
    }
    
    @After
    public void tearDown() { }
    private Item apple = new Item("Apple" , 1.99f , 200, 0.5 );

    @Test
    public void testGetName() {
        assertEquals("Apple", apple.getName());
    }

    @Test
    public void testGetPrice() {
        assertEquals(1.99f, apple.getPrice(),2);
    }

    @Test
    public void testGetStock() {
        assertEquals(200, apple.getStock());
    }



    @Test
    public void testGetWeight() {
        assertEquals(0.5, apple.getWeight(),2);

    }

    @Test
    public void testSetName() {
        apple.setName("Granny Smith Apple");
        assertEquals("Granny Smith Apple", apple.getName());
    }

    @Test
    public void testSetPrice() {
        apple.setPrice(2.00f);
        assertEquals(2.00, apple.getPrice(),2);
    }

    @Test
    public void testSetStock() {
        apple.setStock(300);
        assertEquals(300, apple.getStock());
    }

    @Test
    public void testSetWeight() {
        apple.setWeight(0.40);
        assertEquals(0.40, apple.getWeight(), 2);
    }

    
}
