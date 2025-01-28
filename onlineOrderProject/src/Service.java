import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;


public class Service {

    private static final String FILE_NAME = "C:\\Users\\Steven\\Documents\\Computer Science\\onlineOrderProject\\src\\orders.ser";
    public static ArrayList<Order> Orders = new ArrayList<Order>();
    public static ArrayList<Item> Items = new ArrayList<Item>();

    public static Food apple = new Food("Granny Smith Apple", 1.99, 600, 0.40,"Ambient" , "Fruit");
    public static Item shirt = new Item("Large Tee-Shirt", 15.99,75,1.2);
    public static Item tv = new Item("Sony 72 inch Tv", 305.99, 4, 10.56);
    public static Item guitar = new Item("Acoustic Guitar",239.00,7,2.00);
    public static Food onion = new Food("Yellow Onion", 1.23,600,0.5,"Ambient" , "Vegetable");
    public static Food steak = new Food("16 oz New York Strip Steak",15.67,20,1.00,"Cold","Meat");

    public static LocalDate today = LocalDate.now();
    public static Order order;

    static {
        try {
            order = new Order("Steven Miller", Items,today);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Order order2;

    static {
        try {
            order2 = new Order("Kevin Bacon",Items,today.plusDays(1));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Order order3;

    static {
        try {
            order3 = new Order("Lebron James",Items,today.plusDays(3));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Service(){

        Items.add(apple);
        Items.add(shirt);
        Items.add(tv);
        Items.add(steak);
        Items.add(onion);
        Items.add(guitar);
        readOrderListFile();

    }

    public void readOrderListFile() {
        FileInputStream fis = null;
        ObjectInputStream in = null;
        try {
            fis = new FileInputStream(FILE_NAME);
            in = new ObjectInputStream(fis);
            Orders = (ArrayList) in.readObject();
            in.close();
            if (!Orders.isEmpty()) {
                System.out.println("There are orders in the order list");
            }
        } catch (FileNotFoundException fne) {
            System.out.println("File was not found, a new one will be created");
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    public void writeOrderListFile() {
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream(FILE_NAME);
            out = new ObjectOutputStream(fos);
            out.writeObject(Orders);
            out.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String assignDriver(){
        Random rand = new Random();
        int newR = rand.nextInt(1,3);
        return "Delivery Driver " + String.valueOf(newR);
    }



    public static void sortOrders(){
        int n = Orders.size();
        // Perform bubble sort
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (Orders.get(j).getPickupDate().isAfter(Orders.get(j+1).getPickupDate())) {
                    // Swap elements if they are in the wrong order
                    Order temp = Orders.get(j);
                    Orders.set(j, Orders.get(j + 1));
                    Orders.set(j + 1, temp);
                }
            }
        }

    }



    public static void addOrder(Order order){
        Orders.add(order);


    }

    public static void removeOrder(Order order){
        Orders.remove(order);
    }



    public static void addItem(Item item){
        Items.add(item);
    }

    public static void removeItem(Item item){
        Items.remove(item);
    }

    public static Order getOrder(long orderNumber) throws IOException {
        for(Order order: Orders){
            if (order.getOrderNumber() == orderNumber){
                return order;
            }
            
        }
        Order blank = new Order(null, Items, null);
        return blank;
    }
    public static Item getItem(String itemName){
        for(Item item: Items){
            if (item.getName().equals(itemName)){
                return item;
            }
            
        }
        Item blank = new Item(itemName,0.00f,0,0);
        return blank;
    }
    public static void updateOrder(Order order){
        order.updateSize();
        order.updateTotalPrice();

    }


}
