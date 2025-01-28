import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Delivery extends Order {
    

    public String deliveryDriver;


    

    public Delivery(String customerName, ArrayList<Item> Items,
                    LocalDate pickupDate, String deliveryDriver) throws IOException {
        super(customerName, Items, pickupDate);
        this.deliveryDriver = deliveryDriver;
        updateSize();
        updateTotalPrice();
        updateWeight();
    }


    public String getDeliveryDriver() {
        return deliveryDriver;
    }

    public void setDeliveryDriver(String deliveryDriver) {
        this.deliveryDriver = deliveryDriver;
    }


    @Override
    public String toString() {
        return "order [customerName=" + this.getCustomerName() + ", orderNumber=" + this.getOrderNumber() + ", size=" + this.getSize()
                + ", totalPrice=" + this.getTotalPrice() + ", pickupDate=" + this.getPickupDate() + ", deliveryDriver" + deliveryDriver + "]";
    }

    
    
    
}
