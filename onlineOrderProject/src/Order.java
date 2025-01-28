import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class Order implements Serializable {
    private String customerName;
    private long orderNumber;
    private ArrayList<Item> Items = new ArrayList<Item>();
    private int size;
    private double totalPrice;
    private LocalDate pickupDate;

    private double weight;



    private static final String FILE_NAME = "C:\\Users\\Steven\\Documents\\Computer Science\\onlineOrderProject\\src\\orders.ser";


    public Order(String customerName, ArrayList<Item> Items,
                 LocalDate pickupDate) throws IOException {
        this.customerName = customerName;
        this.Items = Items;
        this.pickupDate = pickupDate;
        assignOrderNumber();
        updateSize();
        updateTotalPrice();
        updateWeight();


    }

    public void assignOrderNumber(){
        ArrayList<Order> Orders = new ArrayList<Order>();
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
        boolean copy = false;
        do {
            copy = false;
            Random rand = new Random();
            long newR = rand.nextLong(9999999);
            this.orderNumber = 10000000 + newR;
            for (Order order: Orders){
                if (this.orderNumber == order.getOrderNumber()){
                    copy = true;
                }
            }
        }while(copy);





    }




    public void updateWeight(){
        for(Item item: this.Items){
            this.weight += item.getWeight();
        }
    }

    public double getWeight(){
        return weight;
    }

    public String getCustomerName() {
        return customerName;
    }


    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }


    public long getOrderNumber() {
        return orderNumber;
    }


    public void setOrderNumber(long orderNumber) {
        this.orderNumber = orderNumber;
    }


    public ArrayList<Item> getItems() {
        return Items;
    }


    public void setItems(ArrayList<Item> Items) {
        this.Items = Items;
    }


    public int getSize() {
        return size;
    }


    public void updateSize() {
        this.size = this.getItems().size() ;
    }


    public double getTotalPrice() {
        return totalPrice;
    }


    public void updateTotalPrice() {
        for (Item item : Items){
            this.totalPrice = totalPrice + item.getPrice();
        }
        
    }


    public LocalDate getPickupDate() {
        return pickupDate;
    }


    public void setPickupDate(LocalDate pickupDate) {
        this.pickupDate = pickupDate;
    }




    @Override
    public String toString() {
        return "order [customerName=" + customerName + ", orderNumber=" + orderNumber + ", size=" + size
                + ", totalPrice=" + totalPrice + ", pickupDate=" + pickupDate + "]";
    }

    




    

}
